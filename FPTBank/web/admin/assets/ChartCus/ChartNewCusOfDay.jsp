
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    // Khai báo các biến biểu đồ ở scope toàn cục
    var myChart1;
    $(function () {
        // chart1
        var ctx = document.getElementById('chart1').getContext('2d');
        myChart1 = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels01new,
                datasets: [{
                        label: 'Number of new customers',
                        data: data01new,
                        backgroundColor: [
                            '#0d6efd'
                        ],
                        lineTension: 0.4,
                        borderColor: [
                            '#0d6efd'
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
                    x: {// Thêm tiêu đề cho trục X
                        title: {
                            display: true, // Hiển thị tiêu đề trục X
                            text: 'Time Period', // Nội dung tiêu đề
                            font: {
                                family: 'Helvetica, Arial, sans-serif',
                                size: 15, // Kích thước chữ
                                weight: 'bold'
                            },
                            color: '#fd7e14' // Màu chữ
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
                        beginAtZero: true
                    }
                }
            }
        });
    });
</script>



