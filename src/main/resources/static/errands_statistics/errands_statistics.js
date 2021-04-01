
angular.module('app').controller('errandsStatisticsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.fillTable = function() {
        $http({
            url: contextPath + '/api/v1/stats',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                category_id: $scope.filter ? $scope.filter.category_id : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages);
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }
});
