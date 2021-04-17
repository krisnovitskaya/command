angular.module('app').controller('errandsCreateController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

   $scope.getEmployee = function () {
        $http({
            url: contextPath + '/api/v1/employees/get_current',
            method: 'GET'
        })
            .then(function (response) {
                console.log("Получение даных текущего сотрудника");
                $scope.Employee = response.data;
            });
    };

       $scope.getUserRoles = function () {
            $http({
                url: contextPath + '/api/v1/users/check_roles',
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
                               url: contextPath + '/api/v1/errands/get_place_types_list',
                               method: 'GET'
                        })
                        .then(function (response) {
                                 console.log("Получение списка типов места назначения");
                                 $scope.PlaceTypesList = response.data;
                        });
         };

         $scope.getPlacesList = function (placeType_id) {
                        $http({
                                 url: contextPath + '/api/v1/errands/get_places_list/' + placeType_id,
                                 method: 'GET'
                        })
                        .then(function (response) {
                                  console.log("Получение списка мест");
                                  $scope.PlacesList = response.data;
                                 });
         };

         $scope.createErrand = function () {
          $http.post(contextPath + '/api/v1/errands/create_errand', $scope.CreatedErrandDto)
                     .then(function (response) {

                         $scope.$parent.errand = null;
                         $scope.errandDetails = null;
                         $scope.CreatedErrandDto = null;
                         $scope.checked = false;
                         alert('Ваша заявка успешно оформлена!');
                         $scope.form.reset();
                         window.alert("Ваша заявка успешно оформлена!");
                     });
         };

    $scope.getUserRoles();
    $scope.getEmployee();
    $scope.getDepartmentsList();
    $scope.getErrandMatterTypesList();
    $scope.getPlaceTypesList();

});