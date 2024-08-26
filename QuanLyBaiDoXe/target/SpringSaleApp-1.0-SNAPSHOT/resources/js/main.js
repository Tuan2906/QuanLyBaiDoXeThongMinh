function deleteBaiDo(endpoint, baiDoId) {
    if (confirm("Bạn chắc chắn xóa không?")) {
        console.log('endpoint', endpoint);
        fetch(endpoint, {
            method: "DELETE"
        })
                .then(res => {
                    if (res.status === 204) {  // NO CONTENT
                        let row = document.getElementById(`baiDo${baiDoId}`);
                        if (row) {
                            row.style.display = "none";
                        }
                    } else {
                        // Ở đây bạn sẽ xử lý các mã trạng thái lỗi khác
                        return res.text().then(text => {
                            // Nếu có lỗi, thông điệp sẽ được hiển thị ở đây
                            const errorDiv = document.getElementById('errorMessage');

                            // Thay thế toàn bộ nội dung HTML bằng chỉ thông điệp lỗi
                            let errorMessage = text;

                            // Nếu nội dung trả về có định dạng HTML, tìm phần thông điệp lỗi
                            const messageStart = text.indexOf("<b>Message</b>");
                            const messageEnd = text.indexOf("</p>", messageStart);

                            if (messageStart !== -1 && messageEnd !== -1) {
                                errorMessage = text.substring(messageStart + 14, messageEnd);
                            }

                            errorDiv.textContent = `Có lỗi xảy ra: ${errorMessage}`;
                            errorDiv.classList.add('alert');  // Thêm lớp CSS alert
                            errorDiv.classList.add('alert-danger');  // Thêm lớp CSS alert-danger                    console.log(`Response error message: ${errorMessage}`);
                        });
                    }
                })
                .catch(error => {
                    // Đây là lỗi mạng hoặc lỗi bất kỳ trong quá trình fetch
                    alert(`Có lỗi xảy ra: ${error}`);
                    console.error('Fetch error:', error);
                });
    }
}



function deleteKhuDo(endpoint, baiDoId) {
    if (confirm("Bạn chắc chắn xóa không?")) {
        console.log('endpoint', endpoint);
        fetch(endpoint, {
            method: "DELETE"
        })
                .then(res => {
                    if (res.status === 204) {  // NO CONTENT
                        let row = document.getElementById(`khudo${baiDoId}`);
                        if (row) {
                            row.style.display = "none";
                        }
                    } else {
                        // Ở đây bạn sẽ xử lý các mã trạng thái lỗi khác
                        return res.text().then(text => {
                            // Nếu có lỗi, thông điệp sẽ được hiển thị ở đây
                            const errorDiv = document.getElementById('errorMessage');

                            // Thay thế toàn bộ nội dung HTML bằng chỉ thông điệp lỗi
                            let errorMessage = text;

                            // Nếu nội dung trả về có định dạng HTML, tìm phần thông điệp lỗi
                            const messageStart = text.indexOf("<b>Message</b>");
                            const messageEnd = text.indexOf("</p>", messageStart);

                            if (messageStart !== -1 && messageEnd !== -1) {
                                errorMessage = text.substring(messageStart + 14, messageEnd);
                            }

                            errorDiv.textContent = `Có lỗi xảy ra: ${errorMessage}`;
                            errorDiv.classList.add('alert');  // Thêm lớp CSS alert
                            errorDiv.classList.add('alert-danger');  // Thêm lớp CSS alert-danger                    console.log(`Response error message: ${errorMessage}`);
                        });
                    }
                })
                .catch(error => {
                    // Đây là lỗi mạng hoặc lỗi bất kỳ trong quá trình fetch
                    alert(`Có lỗi xảy ra: ${error}`);
                    console.error('Fetch error:', error);
                });
    }
}





function deleteClass(endpoint, id) {
    if (confirm("Bạn chắc chắn xóa không?")) {
        console.log('endpoint', endpoint);
        fetch(endpoint, {
            method: "DELETE"
        })
        .then(res => {
            if (res.status === 204) {  // NO CONTENT
                let row = document.getElementById(id);
                if (row) {
                    row.style.display = "none";
                }
            } else {
                // Ở đây bạn sẽ xử lý các mã trạng thái lỗi khác
                return res.text().then(text => {
                    // Nếu có lỗi, thông điệp sẽ được hiển thị ở đây
                    const errorDiv = document.getElementById('errorMessage');

                    // Thay thế toàn bộ nội dung HTML bằng chỉ thông điệp lỗi
                    let errorMessage = text;

                    // Nếu nội dung trả về có định dạng HTML, tìm phần thông điệp lỗi
                    const messageStart = text.indexOf("<b>Message</b>");
                    const messageEnd = text.indexOf("</p>", messageStart);

                    if (messageStart !== -1 && messageEnd !== -1) {
                        errorMessage = text.substring(messageStart + 14, messageEnd);
                    }

                    errorDiv.textContent = `Có lỗi xảy ra: ${errorMessage}`;
                    errorDiv.classList.add('alert');  // Thêm lớp CSS alert
                    errorDiv.classList.add('alert-danger');  // Thêm lớp CSS alert-danger                    console.log(`Response error message: ${errorMessage}`);
                });
            }
        })
        .catch(error => {
            // Đây là lỗi mạng hoặc lỗi bất kỳ trong quá trình fetch
            alert(`Có lỗi xảy ra: ${error}`);
            console.error('Fetch error:', error);
        });
    }
}


function togglePassword() {
    const password = document.querySelector('#password');
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);

    // Toggle the eye / eye-slash icon
    this.querySelector('i').classList.toggle('fa-eye');
    this.querySelector('i').classList.toggle('fa-eye-slash');
}



let regionCount = 0;
function addRegion() {
    regionCount++;

    // Tạo phần tử mới cho vùng
    const newRegion = document.createElement('div');
    newRegion.classList.add('region');
    newRegion.innerHTML = `
                <div class="mb-3 mt-3">
                    <label for="vitri${regionCount}" class="form-label">Vị trí</label>
                    <input type="number" class="form-control" id="vitri${regionCount}" placeholder="Vị trí" name="vitri${regionCount}" />
                </div>
                <div class="mb-3 mt-3">
                    <label for="khoangCach${regionCount}" class="form-label">Khoảng cách</label>
                    <input type="number" class="form-control" id="khoangCach${regionCount}" placeholder="Khoảng cách" name="khoangCach${regionCount}" />
                </div>
                <hr/>

            `;

    // Thêm vùng mới vào phần tử regions
    document.getElementById('regions').appendChild(newRegion);
}



//const navLinks = document.querySelectorAll('.nav-link');
//navLinks.forEach(link => {
//    link.addEventListener('click', function (event) {
//        // Ngăn chặn hành động mặc định nếu cần
////         event.preventDefault();
//
//        // Xóa lớp 'active' từ tất cả các liên kết
//        navLinks.forEach(link => link.classList.remove('active'));
//
//        // Thêm lớp 'active' vào liên kết đã nhấp
//        this.classList.add('active');
//    });
//});