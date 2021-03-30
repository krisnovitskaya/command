angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

   $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/employees/getCurrent',
            method: 'GET',
//            params:{
//                  token: $localStorage.currentUser.token
//            }
        })
            .then(function (response) {
                console.log("Получение даных текущего сотрудника");
                $scope.Employee = response.data;
                console.log(response.data);
            });
    };

 $scope.getDepartmentsList = function () {
                $http({
                    url: contextPath + '/api/v1/departments',
                    method: 'GET'
                })
                    .then(function (response) {
                        $scope.DepartmentsList = response.data;
                        $scope.log("Получение списка отделов");
                        console.log(response.data);
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
                            console.log(response.data);
                        });
        };

         $scope.getErrandMatterTypesList = function () {
                        $http({
                            url: contextPath + '/api/v1/errands/getErrandMatterTypesList',
                            method: 'GET'
                        })
                            .then(function (response) {
                                $scope.ErrandMatterTypesList = response.data;
                                $scope.log("Получение списка типов командировок");
                                console.log(response.data);
                            });
            };

         $scope.getPlaceTypesList = function () {
                        $http({
                               url: contextPath + '/api/v1/errands/getPlaceTypesList',
                               method: 'GET'
                        })
                        .then(function (response) {
                                 $scope.PlaceTypesList = response.data;
                                 $scope.log("Получение списка типов места назначения");
                                 console.log(response.data);
                        });
         };

         $scope.getPlacesList = function (placeTypeId) {
                        $http({
                                 url: contextPath + '/api/v1/errands/getPlacesList' + placeTypeId,
                                 method: 'GET'
                        })
                        .then(function (response) {
                                  $scope.PlacesList = response.data;
                                  $scope.log("Получение списка мест");
                                  console.log(response.data);
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



    $scope.fillTable();
    $scope.getDepartmentsList();
    $scope.getErrandMatterTypesList();
    $scope.getPlaceTypesList();

});