(function(angular) {
  angular.module("test-data-drivers.controllers", []);
  angular.module("test-data-drivers.services", []);
  angular.module("test-data-drivers", ["ngResource", "test-data-drivers.controllers", "test-data-drivers.services"]);
}(angular));