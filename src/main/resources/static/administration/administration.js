angular.module('app').controller('administrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

<<<<<<< HEAD
    $scope.getAllEmployees = function() {
    $http.get(contextPath + "/api/v1/employees/all")
        .then(resp => {
                $scope.employeeList = resp.data;
=======
    $http.get(contextPath + "/api/v1/employees/all")
        .then(resp => {
                $scope.emploeeList = resp.data;
            },
            resp => {
                console.error(resp);
            });

    $http.get(contextPath + "/api/v1/departments/all")
        .then(resp => {
                $scope.departmentList = resp.data;
            },
            resp => {
                console.error(resp);
            });

    $http.get(contextPath + "/api/v1/positions/all")
        .then(resp => {
                $scope.positionList = resp.data;
            },
            resp => {
                console.error(resp);
            });

    $http.get(contextPath + "/api/v1/users/all")
        .then(resp => {
                $scope.userList = resp.data;
>>>>>>> remotes/origin/eagromova-Task27-28-continue
            },
            resp => {
                console.error(resp);
            });
<<<<<<< HEAD
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
                    $scope.editEmployee = null;
=======




    $scope.employee = null;

    $scope.create = function (employee) {

        $http.post(contextPath + "/api/v1/employees/new", $scope.employee = employee)
            .then(resp => {
                    $scope.employee = null;
>>>>>>> remotes/origin/eagromova-Task27-28-continue
                },
                resp => {
                    console.error(resp);
                });
    }

<<<<<<< HEAD
    $scope.delete = function (emp) {
        $http.delete(contextPath + "/api/v1/employees/delete/" + emp.id)
            .then(resp => {
                delete $scope.employeeList[emp.id]
            },
                resp => {
                    console.error(resp);
                });
    }

    $scope.edit = function (emp) {
        $scope.employee = emp;

    }

    $scope.getAllEmployees();
    $scope.getAllDepartments();
    $scope.getAllPositions();
    $scope.getAllUsers();

=======
    $scope.delete = function (employee) {
        $http.delete(contextPath + "/api/v1/employees/delete/" + employee.id)
    }

    $scope.edit = function (employee) {
        $http.get(contextPath + "/api/v1/employees/edit/" + employee.id)
    }

>>>>>>> remotes/origin/eagromova-Task27-28-continue
});