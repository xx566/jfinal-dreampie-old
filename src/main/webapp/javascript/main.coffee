require.config
  baseUrl: '/'
  paths:
    'jQuery': 'webjars/jquery/1.11.1/jquery.min'
    'angular': 'webjars/angularjs/1.3.0-beta.15/angular.min'
    'firebase': ['https://cdn.firebase.com/v0/firebase', 'webjars/firebase/1.0.17/firebase']
    'angularFire': 'webjars/angularFire/0.7.1/angularfire.min'
  shim:
    'angularFire': {deps: ['angular', 'firebase']}

require ['jQuery', 'routes'], ($) ->
  $ ->
    angular.bootstrap document, ['sevless']