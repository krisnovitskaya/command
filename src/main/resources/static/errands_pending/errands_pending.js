angular.module('app').controller('errandsPendingController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    const PAGINATION_DIAPASON = 2;

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/errands/pending',
            method: 'GET',
            params: {
                employee: $scope.filter ? $scope.filter.employee : null,
                errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
                dateStart: $scope.filter ? $scope.filter.dateStart : null,
                dateEnd: $scope.filter ? $scope.filter.dateEnd : null,
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

    $( function() {
        $( "#datepicker" ).datepicker();
    } );

});