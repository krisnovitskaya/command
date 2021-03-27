angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

 $scope.getDepartmentsList = function () {
                $http({
                    url: contextPath + '/api/v1/departments',
                    method: 'GET'
                })
                    .then(function (response) {
                        $scope.DepartmentsList = response.data;
                        $scope.log("Получение списка отделов");
                    });
    };

     $scope.getEmployeesList = function (departmentId) {
                    $http({
                        url: contextPath + '/api/v1/employees/get/' + departmentId,
                        method: 'GET'
                    })
                        .then(function (response) {
                            $scope.EmployeesList = response.data;
                            $scope.log("Получение списка сотрудников отделов");
                        });
        };

         $scope.getErrandMatterTypesList = function () {
                        $http({
                            url: contextPath + '/api/v1/errands',
                            method: 'GET'
                        })
                            .then(function (response) {
                                $scope.ErrandMatterTypesList = response.data;
                                $scope.log("Получение списка типов командировок");
                            });
            };

         $scope.getPlaceTypesList = function () {
                        $http({
                               url: contextPath + '/api/v1/errands',
                               method: 'GET'
                        })
                        .then(function (response) {
                                 $scope.PlaceTypesList = response.data;
                                 $scope.log("Получение списка типов места назначения");
                        });
         };

         $scope.getPlacesList = function (placeTypeId) {
                        $http({
                                 url: contextPath + '/api/v1/errands/get/' + placeTypeId,
                                 method: 'GET'
                        })
                        .then(function (response) {
                                  $scope.PlacesList = response.data;
                                  $scope.log("Получение списка мест");
                                 });
         };

         $scope.createErrand = function () {
          $http.post(contextPath + '/api/v1/errands', $scope.errand, $scope.errandDetails)
                     .then(function (response) {
                         $scope.errand = null;
                         $scope.errandDetails=null;
                         alert('Ваша заявка успешно оформлена');
                     });
         };



    $scope.getDepartmentsList();
    $scope.getErrandMatterTypesList();
    $scope.getPlaceTypesList();

});