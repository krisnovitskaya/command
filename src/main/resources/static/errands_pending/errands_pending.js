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
            url: contextPath + '/api/v1/errands/matters',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandMatterTypes = response.data;
                console.log($scope.ErrandMatterTypes.length + ' matter types loaded');
            });
    }

    getErrandStatusTypes = function() {
        $http({
            url: contextPath + '/api/v1/errands/statuses',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandStatusTypes = response.data;
                console.log($scope.ErrandStatusTypes.length + ' status types loaded');
            });
    }


    $scope.errandDetails = function (errandId) {
        $http({
            url: contextPath + '/api/v1/errands/get_details',
            method: 'POST',
            params: {
                errandId: errandId,
            }
        })
            .then(function (response) {
                $scope.ErrandDetails = response.data.body;
                console.log('Details loaded');
            });
    }

    $scope.confirmErrand = function (errandId) {
        $http({
            url: contextPath + '/api/v1/errands/updateStatus',
            method: 'POST',
            params: {
                errandId: errandId,
                status: 'CONFIRMED'
            }
        })
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.rejectErrand = function (errandId) {
        $http({
            url: contextPath + '/api/v1/errands/updateStatus',
            method: 'POST',
            params: {
                errandId: errandId,
                status: 'REJECTED'
            }
        })
            .then(function (response) {
                $scope.fillTable();
            });
    }

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/errands/pending',
            method: 'GET',
            params: {
                employee: $scope.filter ? $scope.filter.employee : null,
                errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
                dateStart: $scope.filter ? $scope.filter.dateStart : null,
                dateStart2: $scope.filter ? $scope.filter.dateStart2 : null,
                errandStatusType: $scope.filter ? $scope.filter.errandStatusType : null,
                p: pageIndex
            }
        })
            .then(function (response) {
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

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })

    getEmployees();
    getErrandMatterTypes();
    getErrandStatusTypes();
    $scope.fillTable();

});