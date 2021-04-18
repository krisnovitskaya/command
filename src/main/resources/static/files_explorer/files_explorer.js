angular.module('app').controller('filesExplorerController',
    function ($scope, $http) {
        $scope.setFiles = function (uFiles) {
            $scope.uFiles = uFiles;
        }

        $scope.uploadFiles = function () {
            var files = $scope.uFiles;
            for (var i = 0; i < files.length; i++) {
                $scope.uploadFile(files[i]);
            }
        };

        $scope.uploadFile = function (file) {
            var fileReader = new FileReader();
            fileReader.onload = function () {
                var fileData = fileReader.result;
                $scope.sendRequest($scope.fillRequest(file.name, fileData));
            };
            fileReader.readAsDataURL(file);
        };

        $scope.fillRequest = function (fileName, fileData) {
            let data = [{
                'id': '',
                'fileName': fileName,
                'fileData': fileData,
                'authorId': 2,
                'errandId': 2,
                'deleted': false
            }];
            return data;
        };

        $scope.sendRequest = function (requestData) {
            $http({
                url: './api/v1/files/uploads',
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                data: JSON.stringify(requestData)
            }).then(function (response) {
                $scope.fillFilesList([]);
            }).then(function (error) {
                console.log(error);
            });
        };

        $scope.fillFilesList = function (errandsIdsList) {
            $http({
                url: './api/v1/files/listfiles',
                method: 'POST',
                headers: {'Content-type': 'application/json'},
                data: errandsIdsList
            }).then(function (response) {
                console.log("Получение списка загруженных в систему файлов.");
                $scope.filesDtosList = response.data;
            });
        };

        $scope.download = function (id) {
            $http({
                url: './api/v1/files/download',
                method: 'POST',
                headers: {'Content-type': 'application/json'},
                data: id,
                responseType: 'arraybuffer'
            }).then(function (response) {
                console.log("Скачивание файла id = " + id);
                var file = new Blob([response.data]);
                var url = window.URL || window.webkitURL || window.mozURL || window.msURL;
                var downloadLink = angular.element('<a></a>');
                downloadLink.attr('href', url.createObjectURL(file));
                downloadLink.attr('target', '_self');
                downloadLink.attr('download', response.headers('FileName'));
                downloadLink[0].click();
            });
        };

        $scope.delete = function (id) {
            $http({
                url: './api/v1/files/delete',
                method: 'DELETE',
                headers: {'Content-type': 'application/json'},
                data: id
            }).then(function (response) {
                console.log("Удаление файла.");
                $scope.filesDtosList = response.data;
                $scope.fillFilesList([]);
            });
        };
        $scope.fillFilesList([]);
    }
);