angular.module('app').controller('errandsStatisticsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.fillTable = function(pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/statistics',
            method: 'GET',
            params: {
                dep: $scope.filter ? $scope.filter.dep : null,
                empl: $scope.filter ? $scope.filter.empl : null,
                pl_type: $scope.filter ? $scope.filter.pl_type : null,
                place: $scope.filter ? $scope.filter.place : null,
                er_type: $scope.filter ? $scope.filter.er_type : null,
                er_status: $scope.filter ? $scope.filter.er_status : null,
                date_from: $scope.filter ? $scope.filter.date_from : null,
                date_to: $scope.filter ? $scope.filter.date_to : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.ErrandsPage = response.data;
                console.log(response.data);
                $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ErrandsPage.totalPages);
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.getDepsList = function () { $http({
            url: contextPath + '/api/v1/departments',
            method: 'GET'
        })
            .then(function (response) {
                $scope.DepsList = response.data;
            });
    };

    $scope.getEmployeesList = function () { $http({
            url: contextPath + '/api/v1/employees',
            method: 'GET'
        })
            .then(function (response) {
                $scope.EmpList = response.data;
            });
    };

    $scope.getPlaceTypeList = function () { $http({
            url: contextPath + '/api/v1/statistics/place_types',
            method: 'GET'
        })
            .then(function (response) {
                $scope.PlaceTypes = response.data;
            });
    };

    $scope.getPlaces = function () { $http({
            url: contextPath + '/api/v1/statistics/places',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Places = response.data;
            });
    };

    $scope.getErrandsMatters = function () { $http({
            url: contextPath + '/api/v1/errands/matters',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Matters = response.data;
            });
    };

    $scope.getErrandStatuses = function () { $http({
            url: contextPath + '/api/v1/statistics/statuses',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Statuses = response.data;
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.getDepsList();
    $scope.getEmployeesList();
    $scope.getPlaceTypeList();
    $scope.getPlaces();
    $scope.getErrandsMatters();
    $scope.getErrandStatuses();

});
