angular.module('app').controller('administrationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.empl = $location.search();


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
            .then(resp => {
                    $scope.employee = null;
                },
                resp => {
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

    $scope.isNew = function() {
        return $scope.empl.id == null;
    }

    $scope.isEdit = function() {
        return $scope.empl.id > 0;
    }

    $scope.getAllEmployees();
    $scope.getAllDepartments();
    $scope.getAllPositions();
    $scope.edit();

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
