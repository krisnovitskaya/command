angular.module('app').controller('errandsEditController', function ($rootScope, $scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8989/errands';

    $scope.ErrandDetails = $rootScope.ErrandDetails;
    if (!$rootScope.ErrandDetails) {
        window.location.href = '#!/errands_pending';
        return;
    }

    $scope.updateErrand = function () {
        $http.post(contextPath + '/api/v1/errands/update', $scope.ErrandDetails)
            .then(function (response) {

                alert('Данные обновлены');
                window.location.href = '#!/errands_pending';            });
    };

    let getErrandMatterTypes = function () {
        $http({
            url: contextPath + '/api/v1/errands/matters',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandMatterTypes = response.data;
                console.log($scope.ErrandMatterTypes.length + ' matter types loaded');
            });
    }

    let getErrandStatusTypes = function () {
        $http({
            url: contextPath + '/api/v1/errands/statuses',
            method: 'GET'
        })
            .then(function (response) {
                $scope.ErrandStatusTypes = response.data;
                console.log($scope.ErrandStatusTypes.length + ' status types loaded');
            });
    }

    let getPlaceTypesList = function () {
        $http({
            url: contextPath + '/api/v1/errands/get_place_types_list',
            method: 'GET'
        })
            .then(function (response) {
                $scope.PlaceTypesList = response.data;
                console.log($scope.PlaceTypesList.length + ' place types loaded');
            });
    };

    let getPlacesList = function (placeType_id) {
        $http({
            url: contextPath + '/api/v1/errands/get_places_list/' + placeType_id,
            method: 'GET'
        })
            .then(function (response) {
                $scope.PlacesList = response.data;
                console.log($scope.PlacesList.length + ' places loaded');
            });
    };

    getErrandMatterTypes();
    getErrandStatusTypes();
    getPlaceTypesList();
    getPlacesList($scope.ErrandDetails.placeTypeId);

});