angular.module('app').controller('administrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.getAllEmployees = function() {
    $http.get(contextPath + "/api/v1/employees/all")
        .then(resp => {
                $scope.employeeList = resp.data;
            },
            resp => {
                console.error(resp);
            });
    }

    $scope.getAllDepartments = function() {
        $http.get(contextPath + "/api/v1/departments/all")
            .then(resp => {
                    $scope.departmentList = resp.data;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.getAllPositions = function() {
        $http.get(contextPath + "/api/v1/positions/all")
            .then(resp => {
                    $scope.positionList = resp.data;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.getAllUsers = function() {
        $http.get(contextPath + "/api/v1/users/all")
            .then(resp => {
                    $scope.userList = resp.data;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.employee = null;

    $scope.create = function (employee) {

        $http.post(contextPath + "/api/v1/employees", $scope.employee = employee)
            .then(resp => {
                    $scope.employee = null;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.delete = function (employee) {
        $http.delete(contextPath + "/api/v1/employees/delete/" + employee.id)
    }

    $scope.edit = function (employee) {
        $http.get(contextPath + "/api/v1/employees/edit/" + employee.id)
    }

    $scope.getAllEmployees();
    $scope.getAllDepartments();
    $scope.getAllPositions();
    $scope.getAllUsers();

});