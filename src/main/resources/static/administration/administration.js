angular.module('app').controller('administrationController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $http.get("api/v1/employee/all")
        .then(resp => {
                $scope.emploeeList = resp.data;
            },
            resp => {
                console.error(resp);
            });

    $scope.name = '';
    $scope.middleName = '';
    $scope.surname = '';

    $scope.create = function (name, middleName, surname) {

        $http.post("api/v1/employee/new", {'name': name, 'middleName' : middleName, 'surname' : surname})
            .then(resp => {
                    $scope.emploeeList.push(resp.data);
                },
                resp => {
                    console.error(resp);
                });

        $scope.name = '';
        $scope.middleName = '';
        $scope.surname = '';
    }

    $scope.delete = function (employee) {
        $http.delete("api/v1/employee/" + item.id)
            .then(resp => {
                    let ix = $scope.shoppingItemList.map(item => item.id).indexOf(item.id);
                    $scope.shoppingItemList.splice(ix, 1);
                },
                resp => {
                    console.error(resp);
                });
    }

});