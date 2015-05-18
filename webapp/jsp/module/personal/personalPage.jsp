<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <div class="detail_modal" id="todo_modal"style="display:none">
        <div class="light_box"></div>
        <div class="task">
            <div class="title" id="title_edit"><textarea type="text" placeholder="To do..."></textarea></div>
            <div class="detail_wrapper">
                <span class="tid" id="tid_edit"></span>
                <span class="party" id="party_edit"></span>
                <span class="due_date" id="due_date_edit"></span>
                <textarea class="note" id="note_edit"></textarea>
            </div>
            <div class="btn_wrapper">
                <button class="edit_btn">save</button>
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
					<span class="date">~ ${yesterday}</span>
				</div>
				<ul class="past">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Today</h1>
					<span class="date">${today}</span>
				</div>
				<ul class="today">
				</ul>
			</section>
			<section>
				<div class="category">
					<h1 class="name">Future</h1>
					<span class="date">${tomorrow} ~</span>
				</div>
				<ul  class="future">
				</ul>
			</section>
		</main>
    </div>
    <script language="javascript" type="text/javascript" src="/commons/js/personal/ubuntudoPersonal.js"></script>
    <script language="javascript" type="text/javascript" src="/commons/js/dataManager.js"></script>
	<script language="javascript" type="text/javascript" src="/commons/js/todoManager.js"></script>
    <script language="javascript" type="text/javascript" src="/commons/js/modalManager.js"></script>    
	<script language="javascript" type="text/javascript" src="/commons/js/utility.js"></script>
    <script language="javascript" type="text/javascript" src="/commons/js/searchManager.js"></script>

</body>
</html>