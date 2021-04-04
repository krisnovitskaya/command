(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage', 'angular-jwt', 'ui.bootstrap'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html',
                controller: 'mainController'
            })
            .when('/main', {
                templateUrl: 'main/main.html',
                controller: 'mainController'
            })
            .when('/administration', {
                templateUrl: 'administration/administration.html',
                controller: 'administrationController'
            })
            .when('/errands_create', {
                templateUrl: 'errands_create/errands_create.html',
                controller: 'errandsCreateController'
            })
            .when('/errands_pending', {
                templateUrl: 'errands_pending/errands_pending.html',
                controller: 'errandsPendingController'
            })
            .when('/errands_statistics', {
                templateUrl: 'errands_statistics/errands_statistics.html',
                controller: 'errandsStatisticsController'
            })
            .when('/user_profile', {
                templateUrl: 'user_profile/user_profile.html',
                controller: 'userProfileController'
            })
            .when('/errands_current', {
                templateUrl: 'errands_current/errands_current.html',
                controller: 'postController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/employee_form', {
                templateUrl: 'administration/employee_form.html',
                controller: 'administrationController'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage, $location, jwtHelper) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.currentUser = function() {
        return $localStorage.currentUser.username;
    }

    $scope.isUserLogged = function() {
        return !!$localStorage.currentUser == null;
    }

    //функция подсвечивает пункт меню, на который ткнули
    //изначально активным выставляю main
    $(document).ready(function () {
        $('.nav-item').on('click', function () {
            $('.nav-item').not(this).removeClass('active');
            $(this).toggleClass('active');
        });
    });


});



