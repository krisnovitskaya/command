angular.module('app').controller('userProfileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.getProfile = function() {
            $http({
                        url: contextPath + '/api/v1/employees/profile',
                        method: 'GET'
                    })
                        .then(function (response) {
                            $scope.Profile = response.data;
                        });
        }

    $scope.changePassword = function() {
          $http({
                    url: contextPath + '/api/v1/users',
                    method: 'POST',
                    data: $scope.ChangePassword,
                    })
                   .then(
                            function successCallback(response) {
                                $scope.ChangePassword = null;
                                alert('Password has been updated');
                            }, function errorCallback(response){
                                if(!angular.equals(response.data.message,"")){
                                    alert(response.data.message);
                                }
                                $scope.ChangePassword = null;
                            });
        };



    $scope.getProfile();
});