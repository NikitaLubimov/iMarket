angular.module('iMarket').controller('storeController', function ($scope, $http, $location, $localStorage) {

    const contextPathCoreService = 'http://localhost:5555/core/api/v1';
    const contextPathCartsService = 'http://localhost:5555/cart/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPathCoreService + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.CartProductAdd = function (productId) {
        $http.post(contextPathCartsService + '/cart/' + $localStorage.iMarketGuestCartId + '/add/' + productId).then(function () {

        })
    };

    $scope.ProductDelete = function (productId) {
        $http.delete(contextPathCoreService + "/products/" + productId).then(function () {
            //http://localhost:5555/core/api/v1/products/1
            $scope.loadProducts();
        })
    };

    $scope.addProduct = function (product) {
        $http.post(contextPathCoreService + '/products', product).success(function () {
            $scope.product.title = '';
            $scope.product.cost = '';
        }).then(function () {
            $scope.loadProducts();
        })
    };

    $scope.loadProducts();

});