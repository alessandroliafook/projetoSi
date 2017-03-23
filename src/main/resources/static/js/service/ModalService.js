/**
 * Created by lucasdiniz on 23/03/17.
 */
app.service('ModalService', function ($mdDialog) {

    var self = this;

    return {

        showConfirm: function (event, title, message) {

            var confirm = $mdDialog.confirm()
                .title(title)
                .textContent(message)
                .ariaLabel('confirmModal')
                .targetEvent(event)
                .ok('Ok');

            $mdDialog.show(confirm).then(function () {
                // $scope.status = 'You decided to get rid of your debt.';
            }, function () {
                //$scope.status = 'You decided to keep your debt.';
            });
        }


    }
});
