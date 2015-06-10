angular.module('app', ['ngRoute'])

        .config(function ($routeProvider) {
            $routeProvider
                    .when('/inicio', {
                        controller: 'inicioController',
                        templateUrl: 'app/views/inicio.html'
                    })
                    .when('/404', {
                        controller: '404Controller',
                        templateUrl: 'app/views/404.html'
                    })
                    .when('/lisSolicitudCotizacion', {
                        controller: 'lisSolicitudCotizacionController',
                        templateUrl: 'app/views/lisSolicitudCotizacion.html'
                    })
                    .when('/' ,{ 
                        redirectTo: '/inicio' 
                    })
                    .otherwise({
                        redirectTo: '/404'
                    });
        })

        .controller('appController', function ($scope) {
        })
        
        .controller('inicioController', function ($scope) {
        })

        .controller('404Controller', function ($scope) {
        });
        
//        .controller('lisSolicitudCotizacionController', function ($scope) {
//            $scope.click = function(){
//                console.log($scope.yourName);
//            };
//        });


