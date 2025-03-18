$(function () {


  var ctx = document.getElementById('chart21').getContext('2d');
      var  myChart6 = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Chart of CoverRateRate'],
                datasets: [
                    {
                        label: 'Very Low (<30%)',
                        data: [data21[0]],
                        backgroundColor: '#007BFF',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Low (30%-50%)',
                        data: [data21[1]],
                        backgroundColor: '#DC3545',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Medium-Low (50%-60%)',
                        data: [data21[2]],
                        backgroundColor: '#FD7E14',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Medium (60%-70%)',
                        data: [data21[3]],
                        backgroundColor: '#FFC107',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'High (70%-80%)',
                        data: [data21[4]],
                        backgroundColor: '#28A745',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Very High (>80%)',
                        data: [data21[5]],
                        backgroundColor: '#6F42C1',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    }
                ]
            },
            options: {
                maintainAspectRatio: false,
                barPercentage: 0.4,
                categoryPercentage: 0.9,
                plugins: {
                    legend: {
                        position: 'bottom',
                        display: true
                    }
                },
                scales: {
                    x: {
                        ticks: {
                            font: {
                                size: 14,
                                weight: 'bold'
                            }
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Number of Insurances',
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15,
                                weight: 'bold'
                            },
                            color: '#C0C0C0'
                        },
                        beginAtZero: true,
                        max: totalCoverate,
                        ticks: {
                            stepSize: 3,
                        }
                    }
                }
            }
        });
  
 var ctx = document.getElementById('chart2').getContext('2d');
      var  myChart2 = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Chart of FeeRate'],
                datasets: [
                    {
                        label: 'Low Fee (<5%)',
                        data: [data2[0]],
                        backgroundColor: '#dc3545',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Medium Fee (5%-10%)',
                        data: [data2[1]],
                        backgroundColor: '#fd7e14',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'High Fee (10%-15%)',
                        data: [data2[2]],
                        backgroundColor: '#ffc107',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Very High Fee (>15%)',
                        data: [data2[3]],
                        backgroundColor: '#198754',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    }
                ]
            },
            options: {
                maintainAspectRatio: false,
                barPercentage: 0.4,
                categoryPercentage: 0.9,
                plugins: {
                    legend: {
                        position: 'bottom',
                        display: true
                    }
                },
                scales: {
                    x: {
                        ticks: {
                            font: {
                                size: 14,
                                weight: 'bold'
                            }
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: 'Number of Insurances',
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15,
                                weight: 'bold'
                            },
                            color: '#C0C0C0'
                        },
                        beginAtZero: true,
                        max: totalFeerate,
                        ticks: {
                            stepSize: 3,
                        }
                    }
                }
            }
        });

   // chart3 (age)
        var ctx = document.getElementById('chart3').getContext('2d');
       var  myChart3 = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels3,
                datasets: [{
                        data: data3,
                        backgroundColor: [
                            '#008000',
                            '#FF0000'
                           
                        ],
                        borderWidth: 1.5
                    }]
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        display: true
                    },
                    tooltip: {
                        callbacks: {
                            label: function (tooltipItem) {
                                let value = tooltipItem.raw.toFixed(2); // Làm tròn 2 số thập phân
                                return tooltipItem.label + ": " + value + "%";
                            }
                        }
                    }
                }
            }
        });


    // chart5
    var ctx = document.getElementById('chart5').getContext('2d');
       var  myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels5,
                datasets: [{
                        data: data5,
                        backgroundColor: [
                            '#FFFF00',
                            '#0000FF'
                           
                        ],
                        borderWidth: 1.5
                    }]
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        display: true
                    },
                    tooltip: {
                        callbacks: {
                            label: function (tooltipItem) {
                                let value = tooltipItem.raw.toFixed(2); // Làm tròn 2 số thập phân
                                return tooltipItem.label + ": " + value + "%";
                            }
                        }
                    }
                }
            }
        });





    




});