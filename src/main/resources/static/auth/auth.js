angular.module('app').controller('authController', function ($scope, $http, $localStorage, $location, jwtHelper) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                console.log(response.data);
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    window.location.href = '#!/main';
                    console.log($localStorage.currentUser);
                }
            }, function errorCallback() {
                $scope.clearUser();
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        window.location.href = '#!/auth';
    };

    $scope.isUserLoggedIn = function () {
        return !!$localStorage.currentUser;
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.getUserRole = function() {
        if ($scope.isUserLoggedIn()) {
            tokenPayload = jwtHelper.decodeToken($localStorage.currentUser.token);
            $scope.userRoles = tokenPayload.role;
        }
    };

    $scope.isAdmin = function () {
        if ($scope.isUserLoggedIn()) {
            $scope.getUserRole();
            for (let i = 0; i < $scope.userRoles.length; i++) {
                if (angular.equals("ROLE_ADMIN", $scope.userRoles[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    $scope.isMaster = function () {
        if ($scope.isUserLoggedIn()) {
            $scope.getUserRole();
            for (let i = 0; i < $scope.userRoles.length; i++) {
                if (angular.equals("ROLE_MASTER", $scope.userRoles[i])) {
                    return true;
                }
            }
            return false;
        }
    }

    $scope.isPost = function () {
            if ($scope.isUserLoggedIn()) {
                $scope.getUserRole();
                for (let i = 0; i < $scope.userRoles.length; i++) {
                    if (angular.equals("ROLE_POST", $scope.userRoles[i])) {
                        return true;
                    }
                }
                return false;
            }
        }

});