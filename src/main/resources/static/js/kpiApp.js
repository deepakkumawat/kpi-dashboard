angular.module('kpiApp', ['ngMaterial', 'ngMessages'])
    .controller('KPIDashboardController', function ($scope, $http, $filter) {
        // Google chart load
        google.charts.load('current', { 'packages': ['corechart', 'bar'] });
        google.charts.setOnLoadCallback(setupInitialDashboard);

        var kpiDashboard = this;
        var defaultBrand = "ABC";
        var barChart, aovCvrChart, carCacCart, apmRpcChart;
        var barChartHeaders = ['Date', 'Orders', 'Gross revenue', 'Gross margin', 'Add to carts', 'Customer acquired', 'Clicks', 'Marketing cost(USD)'];
        var aovCvrChartHeaders = ['Date', 'AOV', 'CVR'];
        var carCacChartHeaders = ['Date', 'CAC', 'CAR'];
        var apmRpcChartHeaders = ['Date', 'APM', 'RPC'];
        var barChartOptions = {
            chart: {
                title: 'Brand Performance',
                subtitle: 'KPI Dashboard',
            }
        };
        var aovCvrChartOptions = {
            title: 'AOV CVR Timeline',
            curveType: 'function',
            legend: { position: 'bottom' }
        };
        var carCacCartOptions = {
            title: 'CAR CAC Timeline',
            curveType: 'function',
            legend: { position: 'bottom' }
        };
        var apmRpcChartOptions = {
            title: 'Average profit margin & RPC Timeline',
            curveType: 'function',
            legend: { position: 'bottom' }
        };
        kpiDashboard.brands = [{ val: defaultBrand }];

        function setupInitialDashboard() {
            console.log("Google charts loaded, making initial data load");

            // Initial chart setup
            barChart = new google.charts.Bar(document.getElementById('wholeDataChart'));
            barChart.draw(google.visualization.arrayToDataTable([barChartHeaders].concat([[0, 0, 0, 0, 0, 0, 0, 0]])), google.charts.Bar.convertOptions(barChartOptions));

            aovCvrChart = new google.visualization.LineChart(document.getElementById('aov_cvr_DataCart'));
            aovCvrChart.draw(google.visualization.arrayToDataTable([aovCvrChartHeaders].concat([[0, 0, 0]])), aovCvrChartOptions);

            carCacCart = new google.visualization.LineChart(document.getElementById('car_cac_DataCart'));
            carCacCart.draw(google.visualization.arrayToDataTable([carCacChartHeaders].concat([[0, 0, 0]])), carCacCartOptions);

            apmRpcChart = new google.visualization.LineChart(document.getElementById('apm_rpc_DataCart'));
            apmRpcChart.draw(google.visualization.arrayToDataTable([apmRpcChartHeaders].concat([[0, 0, 0]])), apmRpcChartOptions);

            //Data hardcoded dates
            var _fromDate = new Date("2020-01-05");
            var _toDate = new Date("2020-01-05");
            _fromDate.setDate(_fromDate.getDate() - 10);
            kpiDashboard.fromDate = _fromDate;
            kpiDashboard.toDate = _toDate;
            kpiDashboard.brand = defaultBrand;
            var fromDate = _fromDate.toISOString().substring(0, 10);
            var toDate = _toDate.toISOString().substring(0, 10);
            kpiDashboard.loadDate(defaultBrand, fromDate, toDate);
        }

        kpiDashboard.loadDate = function (brand, fromDate, toDate) {
            console.log("Going to load the data from rest for brand: " + brand + ", fromDate: " + fromDate + ", toDate: " + toDate);

            $http.post('/kpi/data/' + brand, { "fromDate": fromDate, "toDate": toDate }).
                then(function (_data) {
                    var barCharData = [];
                    var aovCvrChartData = [];
                    var carCacCartData = [];
                    var apmRpcChartData = [];
                    _data.data.forEach(rec => {
                        barCharData.push([rec.date, rec.orders, rec.grossRevenue, rec.grossMargin, rec.addToCarts, rec.customersAcquired, rec.clicks, rec.marketingCost]);
                        aovCvrChartData.push([rec.date, rec.aov, rec.cvr]);
                        carCacCartData.push([rec.date, rec.car, rec.cac]);
                        apmRpcChartData.push([rec.date, rec.apm, rec.rpc]);
                    });
                    barChart.draw(google.visualization.arrayToDataTable([barChartHeaders].concat(barCharData)), barChartOptions);
                    aovCvrChart.draw(google.visualization.arrayToDataTable([aovCvrChartHeaders].concat(aovCvrChartData)), aovCvrChartOptions);
                    carCacCart.draw(google.visualization.arrayToDataTable([carCacChartHeaders].concat(carCacCartData)), carCacCartOptions);
                    apmRpcChart.draw(google.visualization.arrayToDataTable([apmRpcChartHeaders].concat(carCacCartData)), apmRpcChartOptions);
                });
        }

        kpiDashboard.fetchData = function () {
            var fromDate = kpiDashboard.fromDate.toISOString().substring(0, 10);
            var toDate = kpiDashboard.toDate.toISOString().substring(0, 10);
            console.log("Brand: " + kpiDashboard.brand);
            console.log("fromDate: " + fromDate);
            console.log("toDate: " + toDate);
            kpiDashboard.dataFilters = "Brand: " + kpiDashboard.brand + ", From Date: " + fromDate + ", To Date: " + toDate;
            kpiDashboard.loadDate(defaultBrand, fromDate, toDate);
        };
    });
