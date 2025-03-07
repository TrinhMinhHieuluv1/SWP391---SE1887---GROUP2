/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//checkvalidate
function validateEmail() {
    let username = document.getElementById("emailLoan").value;
    let errorSpan = document.getElementById("emailError");
    let pattern = /^(?!.*\.\.)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!pattern.test(username)) {
        errorSpan.innerText = "Email is invalid";
        errorSpan.style.color = "red";
        errorSpan.style.fontSize = "13px";
    } else {
        errorSpan.innerText = "";
    }
}


function validateLoanRate() {
    let username = document.getElementById("loanRate").value;
    let errorSpan = document.getElementById("nameError");
    let pattern = /^(0|[1-9]\d*)([.,]\d+)?$/;
    if (!pattern.test(username)) {
        errorSpan.innerText = "LoanRate không hợp lệ !! phải là số dương, không chứa chữ cái, không kí tự đặc biệt ngoài[.,]";
        errorSpan.style.color = "red";
        errorSpan.style.fontSize = "13px";
    } else {
        errorSpan.innerText = "";
    }
}

function validateTerm() {
    let username = document.getElementById("duration").value;
    let errorSpan = document.getElementById("nameError2");
    let pattern = /^[1-9]\d*$/;
    if (!pattern.test(username)) {
        errorSpan.innerText = "Kì hạn không hợp lệ !! phải là số nguyên dương, không chứa chữ cái, không kí tự đặc biệt";
        errorSpan.style.color = "red";
        errorSpan.style.fontSize = "13px";
    } else {
        errorSpan.innerText = "";
    }
}

//thansg
document.addEventListener("DOMContentLoaded", () => {
    const selectElement = document.getElementById('numberSelect');

    if (!selectElement) {
        console.error("Không tìm thấy phần tử có id 'numberSelect'.");
        return; // Dừng thực thi nếu không tìm thấy phần tử
    }

    for (let i = 1; i <= 180; i++) {
        const option = document.createElement('option');
        option.value = i;
        option.textContent = i;
        selectElement.appendChild(option);
    }
});
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
    if (!toggleButton)
        return; // Đảm bảo nút tồn tại trước khi xử lý

    const content = document.querySelector(contentSelector);
    if (!content)
        return; // Đảm bảo nội dung tồn tại

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
    function setupTabs(tabsSelector, contentsSelector) {
        const tabActive = `${tabsSelector}--current`;
        const contentActive = `${contentsSelector}--current`;

        document.querySelectorAll(".js-tabs").forEach((tabContainer) => {
            const tabs = tabContainer.querySelectorAll(`.${tabsSelector}`);
            const contents = tabContainer.querySelectorAll(`.${contentsSelector}`);

            if (tabs.length === 0 || contents.length === 0) {
                console.warn(`⚠️ Không tìm thấy tabs (${tabsSelector}) hoặc contents (${contentsSelector}) trong .js-tabs`);
                return; // Dừng hàm nếu không tìm thấy phần tử
            }

            tabs.forEach((tab, index) => {
                tab.addEventListener("click", () => {
                    // Xóa class active khỏi tab và nội dung hiện tại
                    tabContainer.querySelector(`.${tabActive}`)?.classList.remove(tabActive);
                    tabContainer.querySelector(`.${contentActive}`)?.classList.remove(contentActive);

                    // Thêm class active vào tab và nội dung được chọn
                    tab.classList.add(tabActive);
                    contents[index]?.classList.add(contentActive);
                });
            });
        });
    }

    // Gọi hàm setupTabs cho các nhóm tabs khác nhau
    setupTabs("about-introduction__item", "about-introduction__content");
    setupTabs("about-timeline__num", "about-timeline__content");
    setupTabs("faq-question__tab", "faq-question__content");
});



const itemsPerPage = 3;

document.addEventListener("DOMContentLoaded", function () {
    fetch("data/sections.json")
            .then(response => response.json())
            .then(dataSections => {
                Object.keys(dataSections).forEach(sectionId => {
                    createCarousel(sectionId, dataSections[sectionId]);
                });
            })
            .catch(error => console.error("Error loading data:", error));
});

function createCarousel(sectionId, data) {
    let currentIndex = 0;
    let direction = 1;

    const container = document.querySelector(`#container-${sectionId}`);
    const pagination = document.querySelector(`#pagination-${sectionId}`);

    if (!container || !pagination) {
        console.error(`Không tìm thấy phần tử container hoặc pagination cho sectionId: ${sectionId}`);
        return;
    }

    if (!data || data.length === 0) {
        container.innerHTML = `<p class="error-message">Không có dữ liệu để hiển thị.</p>`;
        pagination.innerHTML = "";
        return;
    }

    function renderCards() {
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
            direction = -1;
        } else if (currentIndex === 0) {
            direction = 1;
        }

        currentIndex += direction;
        renderCards();
        renderPagination();
    }

    renderCards();
    renderPagination();
    setInterval(autoSlide, 3000);
}




document.addEventListener("DOMContentLoaded", function () {
    const dateInput = document.getElementById("date");
    if (dateInput) {
        dateInput.addEventListener("click", function () {
            this.showPicker();
        });
    } else {
        console.error('Không tìm thấy phần tử với id="date"');
    }
});

// Đóng mở modal
/**
 * JS toggle
 *
 * Cách dùng:
 * <button class="js-toggle" toggle-target="#box">Click</button>
 * <div id="box">Content show/hide</div>
 */
document.addEventListener("DOMContentLoaded", initJsToggle);

function initJsToggle() {
    const $ = document.querySelector.bind(document);
    const $$ = document.querySelectorAll.bind(document);
    $$(".js-toggle").forEach((button) => {
        const target = button.getAttribute("toggle-target");
        if (!target) {
            console.error(`Cần thêm toggle-target cho: ${button.outerHTML}`);
        }
        button.onclick = (e) => {
            e.preventDefault();

            if (!$(target)) {
                console.error(`Không tìm thấy phần tử "${target}"`);
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

document.addEventListener("DOMContentLoaded", () => {
    const teamList = document.querySelector("#team-list");
    const teamItems = document.querySelectorAll(".team-item");
    const prevButton = document.querySelector(".navigation__prev");
    const nextButton = document.querySelector(".navigation__next");

    // Kiểm tra nếu không tìm thấy phần tử cần thiết
    if (!teamList || teamItems.length === 0 || !prevButton || !nextButton) {
        console.error("Thiếu phần tử cần thiết trong DOM.");
        return; // Dừng thực thi nếu thiếu phần tử
    }

    let currentIndex = 0; // Vị trí ban đầu
    const visibleItems = 4; // Số lượng item hiển thị
    const totalItems = teamItems.length;

    // Hàm tính lại chiều rộng mỗi item (bao gồm cả margin)
    function getItemWidth() {
        return teamItems[0].offsetWidth; // Lấy chiều rộng của item
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
});




document.addEventListener("DOMContentLoaded", function () {
    var feedbackButton = document.getElementById("feedback-button"); // Nút mở form
    var form = document.getElementById("feedback-form");

    if (feedbackButton && form) {
        feedbackButton.addEventListener("click", function () {
            form.scrollIntoView({behavior: "smooth"});
        });
    }
});

// Khi nhấn vào liên kết, cuộn tới form tìm feedback
document.addEventListener("DOMContentLoaded", function () {
    const feedbackLink = document.getElementById('feedback-link');
    const feedbackForm = document.getElementById('feedback-form');

    if (feedbackLink && feedbackForm) {
        feedbackLink.addEventListener('click', function (event) {
            event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết (chuyển trang)

            feedbackForm.scrollIntoView({behavior: 'auto'});

            // Nếu bạn muốn sau khi cuộn, trang vẫn chuyển đến "showfb", hãy thêm đoạn này:
            setTimeout(function () {
                window.location.href = "showfb"; // Chuyển hướng đến "showfb" sau khi cuộn
            }, 500); // Thời gian trễ để cuộn trang trước khi chuyển hướng
        });
    } else {
        console.warn("Không tìm thấy 'feedback-link' hoặc 'feedback-form' trong DOM.");
    }
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

document.addEventListener("DOMContentLoaded", function () {
    fetch("data/products.json")
            .then(response => response.json())
            .then(products => {
                const productContainer = document.getElementById("product-list");
                const modalContainer = document.getElementById("modal-container");

                products.forEach(product => {
                    // Render product items
                    const productItem = document.createElement("div");
                    productItem.classList.add("product-item");
                    productItem.innerHTML = `
                    <img alt="${product.title} Icon" height="50" src="${product.image}" width="50"/>
                    <h3>${product.title}</h3>
                    <p>${product.description}</p>
                    <button class="product-item__btn mil-btn mil-ssm mil-empty js-toggle" toggle-target="#modal-${product.id}">Xem chi tiết</button>
                `;
                    productContainer.appendChild(productItem);

                    // Render modal content
                    const modalItem = document.createElement("div");
                    modalItem.innerHTML = `
                    <div id="modal-${product.id}" class="modal hide" style="--content-width:800px">
                        <div class="modal__content">
                            <button class="modal__close js-toggle" toggle-target="#modal-${product.id}">&times;</button>
                            <div class="row">
                                <div class="col-5">
                                    <div class="modal__img-wrap">
                                        <img src="${product.modalImage}" alt="portrait" class="product-item__img">
                                    </div>
                                </div>
                                <div class="col-7">
                                    <div class="modal__info">
                                        <div class="modal__text">${product.modalText}</div>
                                        <div class="modal__desct">${product.modalDesc}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal__overlay js-toggle" toggle-target="#modal-${product.id}"></div>
                    </div>
                `;
                    modalContainer.appendChild(modalItem);
                });

                // Gọi initJsToggle() sau khi tất cả phần tử đã được render
                initJsToggle();
            })
            .catch(error => console.error("Error loading products:", error));


});


   