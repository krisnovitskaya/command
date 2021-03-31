angular.module('app').controller('postController', function ($scope, $http) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.getCurrent = function () {
        $http({
            url: contextPath + '/api/v1/post',
            method: 'GET'
        })
            .then(function (response) {
                $scope.CurrentErrands = response.data;
            });
    }

    $scope.getCurrent();
});