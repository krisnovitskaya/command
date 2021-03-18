(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
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
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';


    //TODO перенести функционал связанный с безопасностью в контроллер безопасности auth.js.
    // Не забыть изменить контроллер в index.html в панеле навигации.
    $scope.isUserLoggedIn = function () {
        return !!$localStorage.currentUser;
    };

    $scope.getUserRole = function () {
        tokenPayload = jwtHelper.decodeToken($localStorage.currentUser.token);
        $scope.userRoles = tokenPayload.roles;
    };

    //TODO проверить работоспособность. При отсутствии юзера всеравно показывает пункт меню
    $scope.isAdmin = function () {
        $scope.getUserRole();
        for (let i = 0; i < $scope.userRoles.length; i++) {
            if (angular.equals("ROLE_ADMIN", $scope.userRoles[i])) {
                return true;
            }
        }
        return false;
    }

    //TODO проверить работоспособность. При отсутствии юзера всеравно показывает пункт меню
    $scope.isMaster = function () {
        $scope.getUserRole();
        for (let i = 0; i < $scope.userRoles.length; i++) {
            if (angular.equals("ROLE_MASTER", $scope.userRoles[i])) {
                return true;
            }
        }
        return false;
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $location.url('/auth');
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };
    //TODO тут заканчивается секция безопасности


    //функция подсвечивает пункт меню, на который ткнули
    //изначально активным выставляю main
    $(document).ready(function () {
        $('.nav-item').on('click', function () {
            $('.nav-item').not(this).removeClass('active');
            $(this).toggleClass('active');
        });
    });


});



