angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

   $scope.getEmployee = function () {
        $http({
            url: contextPath + '/api/v1/employees/getCurrent',
            method: 'GET'
        })
            .then(function (response) {
                console.log("Получение даных текущего сотрудника");
                $scope.Employee = response.data;
            });
    };

       $scope.getUserRoles = function () {
            $http({
                url: contextPath + '/api/v1/users/checkRoles',
                method: 'GET'
            })
                  .then(
                        function successCallback(response) {
                        $scope.CheckRoles = true;
                        console.log("Начальник")
                        }, function errorCallback(response){
                        $scope.CheckRoles = false;
                        console.log("Не начальник")
                         });
        };

 $scope.getDepartmentsList = function () {
                $http({
                    url: contextPath + '/api/v1/departments/all',
                    method: 'GET'
                })
                    .then(function (response) {
                        console.log("Получение списка отделов");
                        $scope.DepartmentsList = response.data;
                    });
    };

     $scope.getEmployeesList = function (departmentId) {
                    $http({
                        url: contextPath + '/api/v1/employees/get/' + departmentId,
                        method: 'GET'
                    })
                        .then(function (response) {
                            console.log("Получение списка сотрудников отделов");
                            $scope.EmployeesList = response.data;
                        });
        };

         $scope.getErrandMatterTypesList = function () {
                        $http({
                            url: contextPath + '/api/v1/errands/matters',
                            method: 'GET'
                        })
                            .then(function (response) {
                                console.log("Получение списка типов командировок");
                                $scope.ErrandMatterTypesList = response.data;
                            });
            };

         $scope.getPlaceTypesList = function () {
                        $http({
                               url: contextPath + '/api/v1/errands/getPlaceTypesList',
                               method: 'GET'
                        })
                        .then(function (response) {
                                 console.log("Получение списка типов места назначения");
                                 $scope.PlaceTypesList = response.data;
                        });
         };

         $scope.getPlacesList = function (id) {
                        $http({
                                 url: contextPath + '/api/v1/errands/getPlacesList/' + id,
                                 method: 'GET'
                        })
                        .then(function (response) {
                                  console.log("Получение списка мест");
                                  $scope.PlacesList = response.data;
                                 });
         };

         $scope.createErrand = function () {
          $http.post(contextPath + '/api/v1/errands/createErrand', $scope.ErrandDto)
                     .then(function (response) {
                         $scope.errand = null;
//                         $scope.errandDetails = null;
                         $scope.ErrandDto = null;
                         alert('Ваша заявка успешно оформлена');
                     });
         };

    $scope.getUserRoles();
    $scope.getEmployee();
    $scope.getDepartmentsList();
    $scope.getErrandMatterTypesList();
    $scope.getPlaceTypesList();

});