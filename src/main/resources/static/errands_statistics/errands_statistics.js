angular.module('app').controller('errandsStatisticsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.fillTable = function(pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/errands/statistic',
            method: 'GET',
            params: {
                department: $scope.filter ? $scope.filter.department : null,
                employee: $scope.filter ? $scope.filter.employee : null,
                errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
                errandStatusType: $scope.filter ? $scope.filter.errandStatusType : null,
                placeType: $scope.filter ? $scope.filter.placeType : null,
                place: $scope.filter ? $scope.filter.place : null,
                dateStart: $scope.filter ? $scope.filter.dateStart : null,
                dateEnd: $scope.filter ? $scope.filter.dateEnd : null,
                p: pageIndex
            }
        })
            .then(function (response) {
                //$scope.ErrandsPage = response.data;
                $scope.Errands = response.data;
                console.log(response.data);
                //$scope.PaginationArray = $scope.generatePagesInd(1, $scope.ErrandsPage.totalPages);
            });
    };


    $scope.getDepartments = function () { $http({
            url: contextPath + '/api/v1/departments/subordinate',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Departments = response.data;
            });
    };

    $scope.getEmployees = function (departmentId) {
        if(departmentId === '') {
            $scope.Employees = null;
        } else {
            $http({
                url: contextPath + '/api/v1/employees/subordinate/' + departmentId,
                method: 'GET'
            })
                .then(function (response) {
                    console.log("Получение списка сотрудников подразделения");
                    $scope.Employees = response.data;
                });
        }
    };

    $scope.getPlaceTypes = function () {
        $http({
            url: contextPath + '/api/v1/errands/place_types',
            method: 'GET'
        })
            .then(function (response) {
                console.log("Получение списка типов места назначения");
                $scope.PlaceTypes = response.data;
            });
    };

    $scope.getPlaces = function (placeType_id) {
        if(placeType_id === '') {
            $scope.Places = null;
        } else {
            $http({
                url: contextPath + '/api/v1/errands/places/' + placeType_id,
                method: 'GET'
            })
                .then(function (response) {
                    console.log("Получение списка мест");
                    $scope.Places = response.data;
                });
        }
    };

    $scope.getErrandsMatters = function () { $http({
            url: contextPath + '/api/v1/errands/matters',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Matters = response.data;
            });
    };

    $scope.getErrandStatuses = function () { $http({
            url: contextPath + '/api/v1/errands/statuses',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Statuses = response.data;
            });
    };

    // $scope.generatePagesInd = function(startPage, endPage) {
    //     let arr = [];
    //     for (let i = startPage; i < endPage + 1; i++) {
    //         arr.push(i);
    //     }
    //     return arr;
    // }


    $(".extremum-click").click(function () {
        $(this).siblings(".extremum-slide").slideToggle("slow");
    });

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })

    $scope.clearFilter = function () {
        $scope.filter = null;
    }

    $scope.createReport = function(){
         $http({
                url: contextPath + '/api/v1/errands/report',
                method: 'GET',
                params: {
                    department: $scope.filter ? $scope.filter.department : null,
                    employee: $scope.filter ? $scope.filter.employee : null,
                    errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
                    errandStatusType: $scope.filter ? $scope.filter.errandStatusType : null,
                    placeType: $scope.filter ? $scope.filter.placeType : null,
                    place: $scope.filter ? $scope.filter.place : null,
                    dateStart: $scope.filter ? $scope.filter.dateStart : null,
                    dateEnd: $scope.filter ? $scope.filter.dateEnd : null
                        }
                    })
                .then(function (response) {
                    console.log(response);
                    console.log("report send");
                    window.alert("Ваш отчет успешно сформирован на диск D, имя файла: report<текущая дата и время>.xls!");
                        });
        };

    $scope.getErrandsMatters();
    $scope.getErrandStatuses();
    $scope.getPlaceTypes();
    $scope.getDepartments();

});
