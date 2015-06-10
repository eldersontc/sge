angular.module('app')
        .controller('lisSolicitudCotizacionController', ['$scope', '$http', function ($scope, $http) {
                $scope.click = function(){
                    console.log($scope.yourName);
                };
            }]);