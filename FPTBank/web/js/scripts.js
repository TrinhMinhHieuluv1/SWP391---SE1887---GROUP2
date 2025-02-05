/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function load(selector, path) {
    const cached = localStorage.getItem(path);
    if (cached) {
        document.querySelector(selector).innerHTML = cached;
    }

    fetch(path)
            .then((res) => res.text())
            .then((html) => {
                if (html !== cached) {
                    document.querySelector(selector).innerHTML = html;
                    localStorage.setItem(path, html);
                }
            });
}
function toggleFooter() {
    const toggleButton = document.querySelector(".footer--toggle--btn");
    const footer = document.querySelector(".footer");
    const footerTop = document.querySelector(".mil-footer-top");
    const textExpand = toggleButton.querySelector(".text-expand");
    const textCollapsed = toggleButton.querySelector(".text-collapsed");
    const iconArrowRight = toggleButton.querySelector(".icon-arrow-right");

    // Toggle class để chuyển đổi trạng thái ẩn/hiện
    footerTop.classList.toggle("hidden");

    if (footerTop.classList.contains("hidden")) {
        // Khi ẩn
        textExpand.style.display = "none";
        textCollapsed.style.display = "inline";
        iconArrowRight.classList.add("rotated");
    } else {
        // Khi hiển thị
        textExpand.style.display = "inline";
        textCollapsed.style.display = "none";
        iconArrowRight.classList.remove("rotated");

        // Trượt xuống cuối trang
        footer.scrollIntoView({behavior: "smooth", block: "end"});
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const faqItems = document.querySelectorAll(".faq-question__ques-item");
    
    faqItems.forEach(item => {
        const wrap = item.querySelector(".faq-question__ques-wrap");
        const answer = item.querySelector(".faq-question__ques-ans");
        const icon = item.querySelector(".faq-question__ques-icon");

        wrap.addEventListener("click", function () {
            answer.classList.toggle("show");
            icon.classList.toggle("rotated");
        });
    });
});


function toggleContent(toggleButton, contentSelector) {
    if (!toggleButton) return; // Đảm bảo nút tồn tại trước khi xử lý

    const content = document.querySelector(contentSelector);
    if (!content) return; // Đảm bảo nội dung tồn tại

    const textExpand = toggleButton.querySelector(".text-expand");
    const textCollapsed = toggleButton.querySelector(".text-collapsed");
    const iconArrowRight = toggleButton.querySelector(".icon-arrow-right");

    // Toggle class để hiển thị hoặc ẩn nội dung
    content.classList.toggle("show");

    // Thay đổi trạng thái của văn bản và biểu tượng
    const isExpanded = content.classList.contains("show");
    textExpand.style.display = isExpanded ? "none" : "inline";
    textCollapsed.style.display = isExpanded ? "inline" : "none";
    iconArrowRight.classList.toggle("rotated", isExpanded);
}

document.querySelector(".history__btn-more")?.addEventListener("click", function () {
    toggleContent(this, ".about-introduction__more");
});

document.querySelector(".send-money__toggle--btn")?.addEventListener("click", function () {
    toggleContent(this, ".send-money__more");
});

document.querySelector(".borrow-money__toggle--btn")?.addEventListener("click", function () {
    toggleContent(this, ".borrow-money__more");
});

document.addEventListener("DOMContentLoaded", () => {
    const tabsSelector = "about-introduction__item";
    const contentsSelector = "about-introduction__content";

    const tabActive = `${tabsSelector}--current`;
    const contentActive = `${contentsSelector}--current`;

    // Chọn tất cả các container có class .js-tabs
    const tabContainers = document.querySelectorAll(".js-tabs");
    tabContainers.forEach((tabContainer) => {
        const tabs = tabContainer.querySelectorAll(`.${tabsSelector}`);
        const contents = tabContainer.querySelectorAll(`.${contentsSelector}`);
        tabs.forEach((tab, index) => {
            tab.addEventListener("click", () => {
                // Xóa class active khỏi tab và nội dung hiện tại
                tabContainer.querySelector(`.${tabActive}`)?.classList.remove(tabActive);
                tabContainer.querySelector(`.${contentActive}`)?.classList.remove(contentActive);

                // Thêm class active vào tab và nội dung được chọn
                tab.classList.add(tabActive);
                contents[index].classList.add(contentActive);
            });
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const tabsSelector = "about-timeline__num";
    const contentsSelector = "about-timeline__content";

    const tabActive = `${tabsSelector}--current`;
    const contentActive = `${contentsSelector}--current`;

    // Chọn tất cả các container có class .js-tabs
    const tabContainers = document.querySelectorAll(".js-tabs");
    tabContainers.forEach((tabContainer) => {
        const tabs = tabContainer.querySelectorAll(`.${tabsSelector}`);
        const contents = tabContainer.querySelectorAll(`.${contentsSelector}`);
        tabs.forEach((tab, index) => {
            tab.addEventListener("click", () => {
                // Xóa class active khỏi tab và nội dung hiện tại
                tabContainer.querySelector(`.${tabActive}`)?.classList.remove(tabActive);
                tabContainer.querySelector(`.${contentActive}`)?.classList.remove(contentActive);

                // Thêm class active vào tab và nội dung được chọn
                tab.classList.add(tabActive);
                contents[index].classList.add(contentActive);
            });
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const tabsSelector = "faq-question__tab";
    const contentsSelector = "faq-question__content";

    const tabActive = `${tabsSelector}--current`;
    const contentActive = `${contentsSelector}--current`;

    // Chọn tất cả các container có class .js-tabs
    const tabContainers = document.querySelectorAll(".js-tabs");
    tabContainers.forEach((tabContainer) => {
        const tabs = tabContainer.querySelectorAll(`.${tabsSelector}`);
        const contents = tabContainer.querySelectorAll(`.${contentsSelector}`);
        tabs.forEach((tab, index) => {
            tab.addEventListener("click", () => {
                // Xóa class active khỏi tab và nội dung hiện tại
                tabContainer.querySelector(`.${tabActive}`)?.classList.remove(tabActive);
                tabContainer.querySelector(`.${contentActive}`)?.classList.remove(contentActive);

                // Thêm class active vào tab và nội dung được chọn
                tab.classList.add(tabActive);
                contents[index].classList.add(contentActive);
            });
        });
    });
});

const dataSections = {
    vision: [
        { text: "Số 1 về quy mô lợi nhuận", img: "./img/about/number1.svg" },
        { text: "Dẫn đầu về chuyển đổi số trong ngành ngân hàng", img: "./img/about/number2.svg" },
        { text: "Ngân hàng hàng đầu về quản trị rủi ro", img: "./img/about/number3.svg" },
        { text: "Dẫn đầu về chất lượng nguồn nhân lực", img: "./img/about/number4.svg" },
        { text: "Dẫn đầu về quản trị môi trường, xã hội & doanh nghiệp", img: "./img/about/number5.svg" },
        { text: "Phấn đấu phát hành cổ phiếu và niêm yết trên TTCK quốc tế", img: "./img/about/number6.svg" }
    ],
    "core-values": [
        { text: "TIMIBANK không ngừng sáng tạo để mang lại những giá trị thiết thực cho khách hàng", img: "./img/about/value1.svg" },
        { text: "Nỗ lực mở rộng danh mục khách hàng, là nguồn tài sản quý giá và đáng tự hào của TIMIBANK", img: "./img/about/value2.svg" },
        { text: "Lấy sự Chu đáo - Tận tâm với khách hàng làm tiêu chí phấn đấu.", img: "./img/about/value3.svg" },
        { text: "Để xây dựng một ngân hàng quốc gia sánh tầm với khu vực và thế giới.", img: "./img/about/value4.svg" },
        { text: "Luôn nỗ lực tìm kiếm sự Khác biệt trên nền tảng chất lượng và giá trị cao nhất.", img: "./img/about/value5.svg" },
        { text: "Đề cao tính An toàn, bảo mật nhằm bảo vệ tối đa lợi ích của khách hàng, cổ đông", img: "./img/about/value6.svg" }
    ]
};

const itemsPerPage = 3;

function createCarousel(sectionId, data) {
    let currentIndex = 0;
    let direction = 1; // 1: Sang phải, -1: Sang trái

    function renderCards() {
        const container = document.querySelector(`#container-${sectionId}`);
        container.innerHTML = "";

        const pageItems = data.slice(currentIndex, currentIndex + itemsPerPage);

        pageItems.forEach(item => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
                <img src="${item.img}" alt="${item.text}" />
                <p>${item.text}</p>
            `;
            container.appendChild(card);
        });
    }

    function renderPagination() {
        const pagination = document.querySelector(`#pagination-${sectionId}`);
        pagination.innerHTML = "";

        const prevButton = document.createElement("button");
        prevButton.innerHTML = `<i class="fa-solid fa-arrow-left-long pagination__icon"></i>`;
        prevButton.disabled = currentIndex === 0;
        prevButton.classList.toggle("disabled", currentIndex === 0);
        prevButton.addEventListener("click", () => {
            if (currentIndex > 0) {
                currentIndex--;
                renderCards();
                renderPagination();
            }
        });
        pagination.appendChild(prevButton);

        const nextButton = document.createElement("button");
        nextButton.innerHTML = `<i class="fa-solid fa-arrow-right-long pagination__icon"></i>`;
        nextButton.disabled = currentIndex + itemsPerPage >= data.length;
        nextButton.classList.toggle("disabled", currentIndex + itemsPerPage >= data.length);
        nextButton.addEventListener("click", () => {
            if (currentIndex + itemsPerPage < data.length) {
                currentIndex++;
                renderCards();
                renderPagination();
            }
        });
        pagination.appendChild(nextButton);
    }

    function autoSlide() {
        if (currentIndex + itemsPerPage >= data.length) {
            direction = -1; // Đổi hướng sang trái
        } else if (currentIndex === 0) {
            direction = 1; // Đổi hướng sang phải
        }

        currentIndex += direction;
        renderCards();
        renderPagination();
    }

    renderCards();
    renderPagination();
    setInterval(autoSlide, 3000);
}

// Khởi tạo carousel cho từng phần
Object.keys(dataSections).forEach(sectionId => {
    createCarousel(sectionId, dataSections[sectionId]);
});



document.getElementById("date").addEventListener("click", function () {
    this.showPicker();
});

// Đóng mở modal
window.addEventListener("DOMContentLoaded", initJsToggle);

function initJsToggle() {
    const $ = document.querySelector.bind(document);
    const $$ = document.querySelectorAll.bind(document);
    $$(".js-toggle").forEach((button) => {
        const target = button.getAttribute("toggle-target");
        if (!target) {
            document.body.innerText = `Cần thêm toggle-target cho: ${button.outerHTML}`;
        }
        button.onclick = (e) => {
            e.preventDefault();
            if (!$(target)) {
                return (document.body.innerText = `Không tìm thấy phần tử "${target}"`);
            }
            const isHidden = $(target).classList.contains("hide");

            requestAnimationFrame(() => {
                $(target).classList.toggle("hide", !isHidden);
                $(target).classList.toggle("show", isHidden);
            });
        };

        document.onclick = function (e) {
            if (!e.target.closest(target)) {
                const isHidden = $(target).classList.contains("hide");
                if (!isHidden) {
                    button.click();
                }
            }
        };

    });
}

const teamList = document.querySelector("#team-list");
const teamItems = document.querySelectorAll(".team-item");
const prevButton = document.querySelector(".navigation__prev");
const nextButton = document.querySelector(".navigation__next");

let currentIndex = 0; // Vị trí ban đầu
const visibleItems = 4; // Số lượng item hiển thị
const totalItems = teamItems.length;

// Hàm tính lại chiều rộng mỗi item (bao gồm cả margin)
function getItemWidth() {
    return teamItems[0].offsetWidth ; // Lấy chiều rộng của item cộng với margin-right
}

// Cập nhật vị trí hiển thị và trạng thái của nút
function updatePosition() {
    const itemWidth = getItemWidth(); // Tính lại itemWidth mỗi lần cập nhật
    const translateValue = -currentIndex * itemWidth;
    teamList.style.transform = `translateX(${translateValue}px)`;

    // Cập nhật trạng thái của nút Prev
    prevButton.disabled = currentIndex <= 0;
    prevButton.classList.toggle("disabled", currentIndex <= 0);

    // Cập nhật trạng thái của nút Next
    nextButton.disabled = currentIndex >= totalItems - visibleItems;
    nextButton.classList.toggle("disabled", currentIndex >= totalItems - visibleItems);
}

// Sự kiện bấm Next
nextButton.addEventListener("click", () => {
    if (currentIndex < totalItems - visibleItems) {
        currentIndex++;
        updatePosition();
    }
});

// Sự kiện bấm Prev
prevButton.addEventListener("click", () => {
    if (currentIndex > 0) {
        currentIndex--;
        updatePosition();
    }
});

// Khởi tạo vị trí ban đầu
updatePosition();



document.addEventListener("DOMContentLoaded", function () {
    var feedbackButton = document.getElementById("feedback-button"); // Nút mở form
    var form = document.getElementById("feedback-form");

    if (feedbackButton && form) {
        feedbackButton.addEventListener("click", function () {
            form.scrollIntoView({ behavior: "smooth" });
        });
    }
});

// Khi nhấn vào liên kết, cuộn tới form tìm feedback
document.getElementById('feedback-link').addEventListener('click', function (event) {
    event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết (chuyển trang)

    var form = document.getElementById('feedback-form');
    if (form) {
        form.scrollIntoView({behavior: 'auto'});
    }

    // Nếu bạn muốn sau khi cuộn, trang vẫn chuyển đến "showfb", hãy thêm đoạn này:
    setTimeout(function () {
        window.location.href = "showfb"; // Chuyển hướng đến "showfb" sau khi cuộn
    }, 100); // Thời gian trễ để cuộn trang trước khi chuyển hướng
});

//trang prefer

        function showInfo() {
            var selectBox = document.getElementById("serviceSelect");
            var selectedValue = selectBox.value;
            var resultBox = document.getElementById("resultBox");

            if (selectedValue) {
                resultBox.innerHTML = "<strong>Bạn đã chọn:</strong> " + selectedValue;
                resultBox.style.display = "block";
            } else {
                resultBox.style.display = "none";
            }
        }
   