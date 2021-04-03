angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

   $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/employees/getCurrent',
            method: 'GET'
        })
            .then(function (response) {
                console.log("Получение даных текущего сотрудника");
                $scope.Employee = response.data;
               // console.log(response.data);
            });
    };

 $scope.getDepartmentsList = function () {
                $http({
                    url: contextPath + '/api/v1/departments',
                    method: 'GET'
                })
                    .then(function (response) {
                        console.log("Получение списка отделов");
                        $scope.DepartmentsList = response.data;
                       // console.log(response.data);
                        console.log($scope.DepartmentsList.length + ' Departments loaded');
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
                           // console.log(response.data);
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
                               // console.log(response.data);
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
                              //   console.log(response.data);
                        });
         };

         $scope.getPlacesList = function (placeTypeId) {
                        $http({
                                 url: contextPath + '/api/v1/errands/getPlacesList' + placeTypeId,
                                 method: 'GET'
                        })
                        .then(function (response) {
                                  console.log("Получение списка мест");
                                  $scope.PlacesList = response.data;
                                  //console.log(response.data);
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
   // $scope.getEmployeesList();
    $scope.getDepartmentsList();
    $scope.getErrandMatterTypesList();
    $scope.getPlaceTypesList();

});