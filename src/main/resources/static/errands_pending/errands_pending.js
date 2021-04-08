angular.module('app').controller('errandsPendingController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    const PAGINATION_DIAPASON = 2;

<<<<<<< HEAD
    getEmployees = function() {
        $http({
            url: contextPath + '/api/v1/employees/by_master',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Employees = response.data;
                console.log($scope.Employees.length + ' employees loaded');
            });
    }

    getErrandMatterTypes = function() {
        $http({
            url: contextPath + '/api/v1/errands/types',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandMatterTypes = response.data;
                console.log($scope.ErrandMatterTypes.length + ' types loaded');
            });
    }

=======
>>>>>>> remotes/origin/eagromova-Task27-28-continue
    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url: contextPath + '/api/v1/errands/pending',
            method: 'GET',
            params: {
                employee: $scope.filter ? $scope.filter.employee : null,
                errandMatterType: $scope.filter ? $scope.filter.errandMatterType : null,
<<<<<<< HEAD
                dateStart1: $scope.filter ? $scope.filter.dateStart1 : null,
                dateStart2: $scope.filter ? $scope.filter.dateStart2 : null,
=======
                dateStart: $scope.filter ? $scope.filter.dateStart : null,
                dateEnd: $scope.filter ? $scope.filter.dateEnd : null,
>>>>>>> remotes/origin/eagromova-Task27-28-continue
                p: pageIndex
            }
        })
            .then(function (response) {
<<<<<<< HEAD
                console.log(response);
=======
>>>>>>> remotes/origin/eagromova-Task27-28-continue
                $scope.ErrandsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesInd(pageIndex - PAGINATION_DIAPASON, pageIndex + PAGINATION_DIAPASON);
            });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            if (i > 0 && i <= $scope.ErrandsPage.totalPages)
                arr.push(i);
        }
        return arr;
    }

    $scope.clearFilter = function () {
        $scope.filter = null;
        $scope.fillTable();
    }

    $(".extremum-click").click(function () {
        $(this).siblings(".extremum-slide").slideToggle("slow");
    });

<<<<<<< HEAD
    getEmployees();
    getErrandMatterTypes();
    $scope.fillTable();
=======
    $( function() {
        $( "#datepickerStart" ).datepicker();
        $( "#datepickerEnd" ).datepicker();
    } );

    $.datepicker.regional['ru'] = {
        closeText: 'Закрыть',
        prevText: 'Предыдущий',
        nextText: 'Следующий',
        currentText: 'Сегодня',
        monthNames: ['Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'],
        monthNamesShort: ['Янв','Фев','Мар','Апр','Май','Июн','Июл','Авг','Сен','Окт','Ноя','Дек'],
        dayNames: ['воскресенье','понедельник','вторник','среда','четверг','пятница','суббота'],
        dayNamesShort: ['вск','пнд','втр','срд','чтв','птн','сбт'],
        dayNamesMin: ['Вс','Пн','Вт','Ср','Чт','Пт','Сб'],
        weekHeader: 'Не',
        dateFormat: 'dd.mm.yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''
    };
    $.datepicker.setDefaults($.datepicker.regional['ru']);
>>>>>>> remotes/origin/eagromova-Task27-28-continue

});