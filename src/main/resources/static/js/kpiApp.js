angular.module('kpiApp', ['ngMaterial', 'ngMessages'])
  .controller('KPIDashboardController', function($scope, $http, $filter) {
    // Google chart load
    google.charts.load('current', {'packages':['corechart', 'bar']});
    google.charts.setOnLoadCallback(setupInitialDashboard);

    var kpiDashboard = this;
    var defaultBrand = "ABC";
    var barChartHeaders = ['Date', 'Orders', 'Gross revenue', 'Gross margin', 'Add to carts', 'Customer acquired', 'Clicks', 'Marketing cost(USD)'];
    var aovChartHeaders = ['Date', 'AOV'];
    var barChartOptions = {
      chart: {
        title: 'Brand Performance',
        subtitle: 'KPI Dashboard',
      }
    };

    var aovChartOptions = {
      title: 'Company Performance',
      curveType: 'function',
      legend: { position: 'bottom' }
    };
    var barChart, aovChart;
    kpiDashboard.brands = [{val: defaultBrand}];

    function setupInitialDashboard() {
      console.log("Google charts loaded, making initial data load");

      // Initial chart setup
      barChart = new google.charts.Bar(document.getElementById('wholeDataChart'));
      barChart.draw(google.visualization.arrayToDataTable([barChartHeaders]), google.charts.Bar.convertOptions(barChartOptions));
      aovChart = new google.visualization.LineChart(document.getElementById('aovDataCart'));
      aovChart.draw(google.visualization.arrayToDataTable([aovChartHeaders]), aovChartOptions);

      //Loading data for last 10 days
      var _toDate = new Date();
      var _fromDate = new Date();
      _fromDate.setDate(_fromDate.getDate()-10);
      kpiDashboard.fromDate = _fromDate;
      kpiDashboard.toDate = _toDate;
      kpiDashboard.brand = defaultBrand;
      var fromDate = _fromDate.toISOString().substring(0,10);
      var toDate = _toDate.toISOString().substring(0,10);
      kpiDashboard.loadDate(defaultBrand, fromDate, toDate);
    }

    kpiDashboard.loadDate = function(brand, fromDate, toDate) {
      console.log("Going to load the data from rest for brand: " + brand + ", fromDate: " + fromDate + ", toDate: " + toDate);

      $http.post('/kpi/data/' + brand, {"fromDate": fromDate, "toDate": toDate}).
        then(function(_data) {
          var chart1Data = _data.data.map(rec => {
            return [rec.date, rec.orders, rec.grossRevenue, rec.grossMargin, rec.addToCarts, rec.customersAcquired, rec.clicks, rec.marketingCost];
          });

          var aovChartData = _data.data.map(rec => {
            return [rec.date, rec.aov];
          });

          barChart.draw(google.visualization.arrayToDataTable([barChartHeaders].concat(chart1Data)), barChartOptions);
          aovChart.draw(google.visualization.arrayToDataTable([aovChartHeaders].concat(aovChartData)), aovChartOptions);
      });
    }

    kpiDashboard.fetchData = function() {
        var fromDate = kpiDashboard.fromDate.toISOString().substring(0, 10);
        var toDate = kpiDashboard.toDate.toISOString().substring(0, 10);
        console.log("Brand: " + kpiDashboard.brand);
        console.log("fromDate: " + fromDate);
        console.log("toDate: " + toDate);
      kpiDashboard.dataFilters = "Brand: " + kpiDashboard.brand + ", From Date: " + fromDate + ", To Date: " + toDate;
      kpiDashboard.loadDate(defaultBrand, fromDate, toDate);
    };
  });
