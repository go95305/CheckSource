<template>
	<div class="no-account">
		<img
			class="no-account-image"
			src="@/assets/images/shoo_2.png"
			alt="no-account"
		/>
		<div class="no-account-div">
			<span>연결된 계정이 없습니다.</span>
			<router-link class="no-account-link" :to="link"
				>계정 연결하러 가기<span class="material-icons">
					arrow_forward
				</span></router-link
			>
		</div>
	</div>
</template>
<script>
export default {
	name: "MyProjectEditNoAccount",
	props: {
		link: { type: String, default: "/myproject/scm/gitlab" },
	},
};
</script>
<style>
.no-account {
	margin-top: 30px;
	display: flex;
	justify-content: center;
	flex-flow: row wrap;
	align-items: center;
}

.no-account-image {
	height: 120px;
}

.no-account-div {
	margin-left: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-flow: column nowrap;
}

.no-account-link {
	text-decoration: none;
	width: 160px;
	margin-top: 10px;
	padding: 10px;
	cursor: pointer;
	border: 1px solid #eee;
	border-radius: 5px;
	background-color: white;
	color: black;
	box-shadow: 0px 4px 5px #ddd;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
	transition: all 0.3s ease;
}

.no-account-link:hover {
	box-shadow: 0px 4px 5px #ccc;
}
</style>
