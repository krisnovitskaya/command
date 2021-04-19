angular.module('app').controller('administrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.empl = $location.search();

    $scope.arrRoles = [];

    $scope.getAllDetails = function() {
        $http.get(contextPath + "/api/v1/employees/allDetails")
            .then(resp => {
                    $scope.detailsList = resp.data;
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

    $scope.create = function (employee) {
        $http.post(contextPath + "/api/v1/employees/new", $scope.employee = employee)
            .then(
                resp => {
                    console.error(resp);
                });
    }

    $scope.createNewDetails = function (details) {
        $scope.employeeDetails.employee_id = $scope.employee.id;
        $http.post(contextPath + "/api/v1/employees/createDetails", $scope.employeeDetails = details)
            .then(
                $scope.getAllEmployees(),
                $scope.edit(),
                resp => {
                    console.error(resp);
                });
    }

    $scope.createDetails = function (details) {
        $http.post(contextPath + "/api/v1/employees/editDetails", $scope.employeeDetails = details)
            .then(
                $scope.getAllEmployees(),
                $scope.edit(),
            resp => {
                console.error(resp);
            });
    }

    $scope.createNewUser = function (user) {
        // $scope.user.roles = $scope.arrRoles;
        $scope.user.roles = ["ROLE_EMPLOYEE"];
        $scope.user.employee_id = $scope.employee.id;
        $http.post(contextPath + "/api/v1/users/create", $scope.user = user)
            .then(
                $scope.getAllEmployees(),
                $scope.edit(),
                resp => {
                    console.error(resp);
                });
    }

    $scope.createUser = function (user) {
        $scope.user.roles = [$scope.arrRoles];
        $http.post(contextPath + "/api/v1/users/edit", $scope.user = user)
            .then(resp => {
                    console.error(resp);
                });
    }

    $scope.delete = function (emp) {
        $http.delete(contextPath + "/api/v1/employees/delete/" + emp.id)
            .then(resp => {
                delete $scope.employeeList[emp.id]
            },
                resp => {
                    console.error(resp);
                });
    }

    $scope.edit = function () {
        $http.get(contextPath + "/api/v1/employees/edit/" + $scope.empl.id)
        .then(resp => {
                $scope.employee = resp.data;
            },
            resp => {
                console.error(resp);
            });
    }

    $scope.editDetails = function() {
        $http.get(contextPath + "/api/v1/employees/editDetails/" + $scope.empl.id)
            .then(resp => {
                $scope.employeeDetails = resp.data;
            },
            resp => {
                console.error(resp);
            });
    }

    $scope.editUser = function() {
        $http.get(contextPath + "/api/v1/users/edit/" + $scope.empl.id)
            .then(resp => {
                    $scope.user = resp.data;
                },
                resp => {
                    console.error(resp);
                });
    }

    $scope.isNewDetails = function() {
        return $scope.employee.mail == "def";
    }

    $scope.isEditDetails = function() {
        return $scope.employeeDetails.id > 0;
    }

    $scope.isNewUser = function() {
        return $scope.employee.user_name == "def";
    }

    $scope.isEditUser = function() {
        return $scope.user.id > 0;
    }

    $scope.getAllEmployees();
    $scope.getAllDepartments();
    $scope.getAllPositions();
    $scope.edit();
    $scope.editDetails();
    $scope.editUser();
    $scope.getAllDetails();
    $scope.getAllUsers();

});

// $stateProvider
//     .state('employeeDetail', {
//         url: '/employee_form/:employeeId',
//         templateUrl: 'administration/employee_form.html',
//         controller: function($scope, $stateParams, $http){
//             const contextPath = 'http://localhost:8989/errands';
//
//             const empId = $stateParams;
//
//             $http.get(contextPath + "/api/v1/employees/edit/" + empId)
//                 .then(resp => {
//                         $scope.employee = resp.data;
//                     },
//                     resp => {
//                         console.error(resp);
//                     });
//         }
//     });
