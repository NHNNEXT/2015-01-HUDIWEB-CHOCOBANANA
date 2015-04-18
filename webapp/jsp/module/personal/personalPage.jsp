<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="detail_modal" style="display:none">
        <div class="light_box"></div>
        <div class="task">
            <div class="title"><textarea type="text" placeholder="To do..."></textarea></div>
            <div class="detail_wrapper">
                <span class="tid"></span>
                <span class="party"></span>
                <span class="due_date"></span>
                <textarea class="note"></textarea>
            </div>
            <div class="btn_wrapper">
                <button class="edit_btn">edit</button>
                <button class="delete_btn">delete</button>
            </div>
        </div>
    </div>
    
    <div id="container" class="ns_personal">
		<aside>
			<div class="party_pavicon">휴</div>
			<div class="party_pavicon">휴</div>

		</aside>
		<main>
			<section>
				<div class="category">
					<h1 class="name">Past</h1>
					<span class="date">~ 2015.04.06</span>
				</div>
				<ul class="past">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Today</h1>
					<span class="date">2015.04.07</span>
				</div>
				<ul class="today">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Future</h1>
					<span class="date">2015.04.08 ~</span>
				</div>
				<ul  class="future">
				</ul>
			</section>
		</main>
    </div>
    <script src="/commons/js/personal/ubuntudo_personal.js"></script>
	<script src="/commons/js/personal/todolist.js"></script>
    <script src="/commons/js/personal/modal.js"></script>
</body>
</html>