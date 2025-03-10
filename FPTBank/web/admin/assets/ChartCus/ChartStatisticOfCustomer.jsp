
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script>
    // Khai báo các biến biểu đồ ở scope toàn cục
    var myChart1, myChart2, myChart3, myChart4, myChart5;
    $(function () {
        // chart1
        var ctx = document.getElementById('chart1').getContext('2d');
        myChart1 = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels1,
                datasets: [{
                        label: 'Number of new customers',
                        data: data1,
                        backgroundColor: [
                            '#fd7e14'
                        ],
                        lineTension: 0.4,
                        borderColor: [
                            '#fd7e14'
                        ],
                        borderWidth: 3
                    }
                ]
            },
            options: {
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        display: true
                    }
                },
                scales: {
                    y: {
                        title: {
                            display: true, // Hiển thị tiêu đề trục Y
                            text: 'Number of customer', // Nội dung tiêu đề
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15, // Kích thước chữ
                                weight: 'bold'

                            },
                            color: '#C0C0C0' // Màu chữ
                        },
                        beginAtZero: true
                    }
                },
                onClick: function (event, elements) {
                    if (elements.length > 0) {
                        var index = elements[0].index;
                        var selectedMonth = labels1[index];  // Lấy tháng được chọn

                        // Tạo một form ẩn để gửi yêu cầu POST tới servlet
                        var form = document.createElement('form');
                        form.method = 'POST';
                        form.action = 'NewCusByDayOfMonth'; // Đường dẫn tới servlet

                        // Thêm input ẩn để gửi dữ liệu tháng
                        var input = document.createElement('input');
                        input.type = 'hidden';
                        input.name = 'month';
                        input.value = selectedMonth;
                        form.appendChild(input);

                        // Thêm form vào body và submit
                        document.body.appendChild(form);
                        form.submit();
                    }
                }
            }
        });

        // chart 2
        var ctx = document.getElementById('chart2').getContext('2d');
        myChart2 = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Chart of Credit Score'],
                datasets: [
                    {
                        label: 'Poor (300-499)',
                        data: [data2[0]],
                        backgroundColor: '#dc3545',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Fair (500-599)',
                        data: [data2[1]],
                        backgroundColor: '#fd7e14',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Good (600-699)',
                        data: [data2[2]],
                        backgroundColor: '#ffc107',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Very Good (700-799)',
                        data: [data2[3]],
                        backgroundColor: '#198754',
                        borderColor: '#000000',
                        borderWidth: 1.3
                    },
                    {
                        label: 'Excellent (800+)',
                        data: [data2[4]],
                        backgroundColor: '#0d6efd',
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
                            text: 'Number of Customers',
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15,
                                weight: 'bold'
                            },
                            color: '#C0C0C0'
                        },
                        beginAtZero: true,
                        max: totalOfCus,
                        ticks: {
                            stepSize: 5
                        }
                    }
                }
            }
        });


        // chart3 (age)
        var ctx = document.getElementById('chart3').getContext('2d');
        myChart3 = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels3,
                datasets: [{
                        data: data3,
                        backgroundColor: [
                            '#FFB6C1',
                            '#FFD700',
                            '#87CEFA',
                            '#98FB98'
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



        // chart4 (balance)
        var ctx = document.getElementById('chart4').getContext('2d');
        myChart4 = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Chart of Balance'],
                datasets: [
                    {
                        label: 'Basic (<50M)',
                        data: [data4[0]],
                        backgroundColor: [
                            '#ADE8F4' // Basic - Light Cyan Blue  
                        ],
                        lineTension: 0,
                        borderColor: [
                            '#000000'
                        ],
                        borderWidth: 1.3
                    },
                    {
                        label: 'Emerging (50M-200M)',
                        data: [data4[1]],
                        backgroundColor: [
                            '#48CAE4'
                        ],
                        lineTension: 0,
                        borderColor: [
                            '#000000'
                        ],
                        borderWidth: 1.3
                    },
                    {
                        label: 'Middle (200M-500M)',
                        data: [data4[2]],
                        backgroundColor: [
                            '#0096C7'
                        ],
                        lineTension: 0,
                        borderColor: [
                            '#000000'
                        ],
                        borderWidth: 1.3
                    },
                    {
                        label: 'Upper (500M-1B)',
                        data: [data4[3]],
                        backgroundColor: [
                            '#0077B6'
                        ],
                        lineTension: 0,
                        borderColor: [
                            '#000000'
                        ],
                        borderWidth: 1.3
                    },
                    {
                        label: 'VIP (>1B)',
                        data: [data4[4]],
                        backgroundColor: [
                            '#023E8A'
                        ],
                        lineTension: 0,
                        borderColor: [
                            '#000000'
                        ],
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
                                size: 14, // Chỉnh kích thước chữ trên trục X
                                weight: 'bold'
                            }
                        }
                    },
                    y: {
                        title: {
                            display: true, // Hiển thị tiêu đề trục Y
                            text: 'Number of customer', // Nội dung tiêu đề
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15, // Kích thước chữ
                                weight: 'bold'

                            },
                            color: '#C0C0C0' // Màu chữ
                        },
                        beginAtZero: true,
                        max: totalCus2, // Giới hạn tối đa
                        ticks: {
                            stepSize: 5 // Chia bậc mỗi 5 đơn vị
                        }
                    }
                }


            }
        });


        // chart5 (status)
        var ctx = document.getElementById('chart5').getContext('2d');
        myChart5 = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: labels5,
                datasets: [{
                        data: data5,
                        backgroundColor: [
                            '#023E8A',
                            '#fd7e14'

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
                                let value = tooltipItem.raw.toFixed(1); // Làm tròn 1 số thập phân
                                return tooltipItem.label + ": " + value + "%";
                            }
                        }
                    }
                }
            }
        });


    });

</script>
