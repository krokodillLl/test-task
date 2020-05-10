(function(angular) {
  var EmployeeFactory = function($resource) {
    return $resource('/employee/:action/:id',
        {id: '@id'},
    {
      add: {
        method: "POST",
        params: {
          action: 'add'
        }
      },
      update: {
        method: "PUT",
        params: {
          action: 'update'
        }
      },
      remove: {
        method: "DELETE",
        params: {
          action: 'delete'
        }
      },
      getOne: {
        method: "GET"
      },
    });
  };

  EmployeeFactory.$inject = ['$resource'];
  angular.module("test-data-drivers.services").factory("Employee", EmployeeFactory);

  var VacationFactory = function($resource) {
    return $resource('/vacation/:action/:id',
        {id: '@id'},
        {
          add: {
            method: "POST",
            params: {
              action: 'add'
            }
          },
          update: {
            method: "PUT",
            params: {
              action: 'update'
            }
          },
          remove: {
            method: "DELETE",
            params: {
              action: 'delete'
            }
          },
        });
  };

  VacationFactory.$inject = ['$resource'];
  angular.module("test-data-drivers.services").factory("Vacation", VacationFactory);

}(angular));