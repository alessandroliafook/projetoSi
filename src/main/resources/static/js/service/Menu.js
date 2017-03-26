app.service('Menu',function($state, $mdSidenav, Auth) {
	this.getMenuList = function () {
		var options = [];
		opcao0 = {
			titulo: "Página Inicial",
			state: "main",
			icon: "home"
		};

		options.push(opcao0);

		return options;
	}

	this.getMenuOptionsAccount = function () {
		var options = [];
		opcao0 = {
			titulo: "Meus dados",
			state: "contato",
			icon: "account_circle"
		};
		opcao1 = {
			titulo: "Logout",
			state: "login",
			icon: "exit_to_app"
		};

		options.push(opcao0);
		options.push(opcao1);

		return options;
	}

	this.changeSidenav = buildToggler('left');

    function buildToggler(componentId) {
      return function() {
        $mdSidenav(componentId).toggle();
      }
    }

	this.changeState = function(state) {
		if (state == "login") {
			Auth.logout();
		}
		$state.go(state);
	}

	this.currentState = function() {
		return $state.current.name;
	}
});