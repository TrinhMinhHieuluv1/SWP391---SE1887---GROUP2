
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    var options1, options2, options3, options4;
    var chart1, chart2, chart3, chart4;
    $(function () {
        "use strict";
        // chart 1
        options1 = {
            series: [{
                    name: 'Number of contract',
                    data: data1
                }],
            chart: {
                foreColor: '#9ba7b2',
                height: 330,
                type: 'bar',
                zoom: {
                    enabled: false
                },
                toolbar: {
                    show: false
                }
            },
            stroke: {
                width: 0,
                curve: 'smooth'
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: "30%",
                    endingShape: "rounded"
                }
            },
            grid: {
                borderColor: 'rgba(255, 255, 255, 0.15)',
                strokeDashArray: 4,
                yaxis: {
                    lines: {
                        show: true
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'light',
                    type: 'vertical',
                    shadeIntensity: 0.5,
                    gradientToColors: ['#01e195'],
                    inverseColors: true,
                    opacityFrom: 1,
                    opacityTo: 1
                }
            },
            colors: ['#0dfd6d'],
            dataLabels: {
                enabled: false,
                enabledOnSeries: [1]
            },
            xaxis: {
                categories: labels1,
                title: {
                    text: titleOfX, // Đặt tiêu đề cho trục X
                    style: {
                        fontSize: '14px',
                        fontWeight: 'bold',
                        color: '#fd7e14' // Màu chữ tiêu đề
                    },
                    font: {
                        family: 'Helvetica, Arial, sans-serif',
                        size: 18// Kích thước chữ
                    }
                }
            }
        };

        chart1 = new ApexCharts(document.querySelector("#chart1"), options1);
        chart1.render();





// chart 2
        options2 = {
            series: dataOfFeedBack,
            chart: {
                height: 255,
                type: 'pie'
            },
            legend: {
                position: 'bottom',
                show: true  // Hiển thị legend
            },

            colors: ['#FFB6C1',
                '#FFD700',
                '#87CEFA',
                '#98FB98', '#FF8C00'],

            dataLabels: {
                enabled: false
            },
            labels: ['1 star', '2 star', '3 star', '4 star', '5 star'],
            responsive: [{
                    breakpoint: 480,
                    options: {
                        chart: {
                            height: 200
                        },
                        legend: {
                            position: 'bottom'
                        }
                    }
                }]
        };

        chart2 = new ApexCharts(document.querySelector("#chart2"), options2);
        chart2.render();




        // chart 3

        options3 = {
            series: [{
                    name: 'Revenue of month',
                    data: data3
                }],
            chart: {
                foreColor: '#9ba7b2',
                height: 250,
                type: 'area',
                zoom: {
                    enabled: false
                },
                toolbar: {
                    show: false
                }
            },
            stroke: {
                width: 4,
                curve: 'smooth'
            },
            grid: {
                borderColor: 'rgba(255, 255, 255, 0.15)',
                strokeDashArray: 4,
                yaxis: {
                    lines: {
                        show: true
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'light',
                    type: 'vertical',
                    shadeIntensity: 0.5,
                    gradientToColors: ['#01e195'],
                    inverseColors: true,
                    opacityFrom: 1,
                    opacityTo: 1
                }
            },
            colors: ['#0d6efd'],
            dataLabels: {
                enabled: false
            },
            xaxis: {
                categories: labels3
            },
            yaxis: {
                labels: {
                    formatter: function (value) {
                        return value.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                    }
                }
            },
            tooltip: {
                y: {
                    formatter: function (value) {
                        return value.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                    }
                }
            }
        };

        chart3 = new ApexCharts(document.querySelector("#chart3"), options3);
        chart3.render();




        // chart 4

        options4 = {
            series: [{
                    name: 'Revenue of month',
                    data: data4
                }],
            chart: {
                foreColor: '#9ba7b2',
                height: 250,
                type: 'area',
                zoom: {
                    enabled: false
                },
                toolbar: {
                    show: false
                }
            },
            stroke: {
                width: 3,
                curve: 'smooth'
            },
            grid: {
                borderColor: 'rgba(255, 255, 255, 0.15)',
                strokeDashArray: 4,
                yaxis: {
                    lines: {
                        show: true
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'light',
                    type: 'vertical',
                    shadeIntensity: 0.5,
                    gradientToColors: ['#01e195'],
                    inverseColors: false,
                    opacityFrom: 0.8,
                    opacityTo: 0.2
                }
            },
            colors: ['#0d6efd'],
            dataLabels: {
                enabled: false,
                enabledOnSeries: [1]
            },
            xaxis: {
                categories: labels4
            },
            yaxis: {
                labels: {
                    formatter: function (value) {
                        return value.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                    }
                }
            },
            tooltip: {
                y: {
                    formatter: function (value) {
                        return value.toLocaleString('vi-VN', {
                            style: 'currency',
                            currency: 'VND'
                        });
                    }
                }
            }
        };
        chart4 = new ApexCharts(document.querySelector("#chart4"), options4);
        chart4.render();


    });
</script>

