
app.service('Auth', function ($cookies, $state, $http) {
    this.getToken = function () {
        var tokenUser = $cookies.get('token');
        if (tokenUser) {
            $http.defaults.headers.common['token'] = tokenUser;
            return tokenUser;
        } else {
            return null;
        }
    };

    this.saveToken = function (token) {
        $cookies.put('token', token);
        $http.defaults.headers.common['token'] = token;
    };

    this.logout = function () {
        $cookies.remove('token');
        $state.go('login');
    };

    this.verificaStatus = function (status) {
        if (status == 203) {
            $cookies.remove('token');
            $state.go('login');
            return false;
        } else {
            return true;
        }
    };

    if (this.getToken()) {
        $http.defaults.headers.common['token'] = this.getToken();
    }
});