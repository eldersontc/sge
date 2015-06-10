'use strict';

define(['services/routeResolver'], function () {

    var app = angular.module('app', ['routeResolverServices']);

    app.config(['$routeProvider', 'routeResolverProvider', '$controllerProvider',
        '$compileProvider', '$filterProvider', '$provide',
        function ($routeProvider, routeResolverProvider, $controllerProvider,
                $compileProvider, $filterProvider, $provide) {
            debugger;
            app.register =
                    {
                        controller: $controllerProvider.register,
                        directive: $compileProvider.directive,
                        filter: $filterProvider.register,
                        factory: $provide.factory,
                        service: $provide.service
                    };

            var route = routeResolverProvider.route;

            $routeProvider
                    .when('/inicio', route.resolve('inicio'))
                    .when('/404', route.resolve('404'))
                    .when('/lisSolicitudCotizacion', route.resolve('lisSolicitudCotizacion'))
                    .when('/', {redirectTo: '/inicio'})
                    .otherwise({redirectTo: '/404'});

        }]);

    app.controller('appController', function ($scope) {
    });

    return app;
});