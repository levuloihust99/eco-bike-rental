### Các nhược điểm của thiết kế cũ và cải tiến đề xuất

#### 1. Giải quyết yêu cầu phát sinh khi có loại xe mới

*Thiết kế cũ*: 

- Chỉ có một lớp `Bike` và trường `type`, khi đó việc tính tiền sẽ bị phụ thuộc vào trường `type`. Khi phát sinh thêm loại xe mới với cách tính tiền mới, sẽ cần phải sửa lại code.

*Cải tiến*:

- Thêm các lớp con kế thừa lớp `Bike`, mỗi lớp con tương ứng với một loại `Bike`, ví dụ: `NormalBike`, `EBike`, `TwinBike`, `TwinEBike`. Đồng thời, thêm các lớp tính tiền tương ứng với từng loại xe, cụ thể như hình vẽ:
![Improve](https://user-images.githubusercontent.com/49064246/104085935-d3a70b00-5285-11eb-883d-9ccbbf6db543.png)

#### 2. Giải quyết vi phạm nguyên tắc O và D trong SOLID, giảm coupling

*Thiết kế cũ*:

- Lớp `PaymentController` phụ thuộc trực tiếp vào lớp `FeeCalculator`. Điều này sẽ gây nên vấn đề **control coupling** vì `PaymentController` sẽ truyền cho `FeeCalculator` đối tượng `Bike` và việc tính toán của `FeeCalculator` sẽ phụ thuộc vào `type` của đối tượng `Bike` được truyền vào.

- Nguyên tắc O bị vi phạm vì khi có yêu cầu về mở rộng hay chỉnh sửa cách tính tiền, ta sẽ phải sửa code của `FeeCalculator`.

- Nguyên tắc D bị vi phạm vì `PaymentController` bị phụ thuộc trực tiếp vào lớp cung cấp dịch vụ cho nó là `FeeCalculator`.

*Cải tiến*:

- Tương tự như giải quyết yêu cầu phát sinh khi có loại xe mới, ta sẽ để lớp `PaymentController` sử dụng một interface `FeeCalculator`. Cách làm này sẽ giải quyết được cả 3 nhươc điểm đã nêu trên, cụ thể như hình vẽ:

![ImproveDesignPrinciple](https://user-images.githubusercontent.com/49064246/104086209-503ae900-5288-11eb-93f8-bf5e19a07235.png)

#### 3. Giảm bớt sự phụ thuộc giữa hệ thống và Interbank

*Thiết kế cũ*:

- `InterbankInterface` định nghĩa một phương thức `codeToDetail`, chuyển đổi mã lối nhận được từ phía Interbank thành message tương ứng. Việc này không tốt hệ thống sẽ không hiểu được ý nghĩa của message trả về.

*Cải tiến*:

- Thay vì chuyển đổi mã lỗi trả về từ `Interbank` thành message, ta sẽ định nghĩa sẵn các mã lỗi bên phía hệ thống, và nhiệm vụ của `Interbank subsystem` là chuẩn hóa mã lỗi phía ngân hàng về mã lỗi của hệ thống, cụ thể như sau:

    ***Bảng mã lỗi của hệ thống***

    |Code   |Ý nghĩa   |
    |-------|---------------|
    |0  |Giao dịch thành công   |
    |1   |Thông tin thẻ không hợp lệ    |
    |2  |Thẻ không đủ số dư |
    |3  |Lỗi đường truyền   |
    |4  |Giao dịch thất bại |

    Việc chuẩn hóa mã lỗi được thực hiện thông qua hàm `translateCode`, cụ thể trong hình bên dưới.
    </br>

- Mỗi hệ thống ngân hàng sẽ có một lớp `InterbankTransaction` tương ứng. Đối tượng của lớp này chứa thông tin về giao dịch và sẽ được truyền vào hàm `processTransaction` để thực hiện giao dịch. Trong thiết kế cũ, hàm `processTransaction` được định nghĩa trong interface `InterbankInterface` nhận tham số đầu vào là kiểu `Object`. Kiểu dữ liệu này quá generic và không phù hợp. Trong thiết kế cải tiến, kiểu dữ liệu này được đổi thành `PaymentObject`, mỗi hệ thống ngân hàng implement lại `InterbankInterface` sẽ có một lớp `InterbankTransaction` kế thừa lại lớp `PaymentObject`, cụ thể trong hình bên dưới.
</br>

![image](https://user-images.githubusercontent.com/49064246/104087018-394bc500-528f-11eb-8d6f-1a94b868262c.png)

#### 4. Chỉnh sửa, cập nhật SDD

- Phân tích kĩ hơn các nguyên lý SOLID, các mức độ coupling và cohesion trong thiết kế sau khi cải tiến.

- Chỉnh sửa biểu đồ sequence cho phần thiết kế kiến trúc để phù hợp với kiến trúc MVC, cụ thể:

    ***Trước khi sửa***

    ![before](https://user-images.githubusercontent.com/49064246/104088666-22ab6b00-529b-11eb-8f4e-2a3caffa1624.png)

    ***Sau khi sửa***

    ![after](https://user-images.githubusercontent.com/49064246/104088679-2c34d300-529b-11eb-84ae-0a9b6ccde679.png)

#### 5. Chỉnh sửa, cập nhật code

- Đã cập nhật code theo thiết kế mới.
