angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

 $scope.getDepartmentsList = function () {
                $http({
                    url: contextPath + '/api/v1/departments',
                    method: 'GET'
                })
                    .then(function (response) {
                        $scope.DepartmentsList = response.data;
                        scope.log("Получение списка отделов");
                    });
    };

    $scope.getDepartmentsList();

});