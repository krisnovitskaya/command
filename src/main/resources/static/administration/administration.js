angular.module('app').controller('administrationController', function ($scope, $http, $routeParams) {
    const contextPath = 'http://localhost:8989/errands';


    // $stateProvider
    //     .state('employeeDetail', {
    //         url: '/employee_form/:employeeId'
    //     });
    // $scope.id = $stateParams.employeeId;

    const empId = $routeParams.employeeId;

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

    // $scope.employee = null;

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
        $http.get(contextPath + "/api/v1/employees/edit/" + empId)
        .then(resp => {
                $scope.employee = resp.data;
            },
            resp => {
                console.error(resp);
            });

    }

    $scope.getAllEmployees();
    $scope.getAllDepartments();
    $scope.getAllPositions();

});