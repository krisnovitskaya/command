angular.module('app').controller('errandsPendingController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    const PAGINATION_DIAPASON = 2;

    getEmployees = function() {
        $http({
            url: contextPath + '/api/v1/employees/by_master',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Employees = response.data;
                console.log($scope.Employees.length + ' employees loaded');
            });
    }

    getErrandMatterTypes = function() {
        $http({
            url: contextPath + '/api/v1/errands/types',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandMatterTypes = response.data;
                console.log($scope.ErrandMatterTypes.length + ' types loaded');
            });
    }

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/errands/pending',
            method: 'GET',
            params: {
                employee: $scope.filter ? $scope.filter.employee : null,
                errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
                dateStart1: $scope.filter ? $scope.filter.dateStart1 : null,
                dateStart2: $scope.filter ? $scope.filter.dateStart2 : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                console.log(response);
                $scope.ErrandsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesInd(pageIndex - PAGINATION_DIAPASON, pageIndex + PAGINATION_DIAPASON);
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            if (i > 0 && i <= $scope.ErrandsPage.totalPages)
                arr.push(i);
        }
        return arr;
    }

    $scope.clearFilter = function () {
        $scope.filter = null;
        $scope.fillTable();
    }

    $(".extremum-click").click(function () {
        $(this).siblings(".extremum-slide").slideToggle("slow");
    });

    getEmployees();
    getErrandMatterTypes();
    $scope.fillTable();

});