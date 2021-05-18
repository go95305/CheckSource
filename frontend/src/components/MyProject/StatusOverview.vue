<template>
	<div class="depart-status-container">
		<div class="panel project">
			<div class="status-title">
				<span>{{ status.project }}</span
				>프로젝트
			</div>
			<div class="tooltip-content">
				<p class="tooltip-title">{{ msg.project }}</p>
			</div>
		</div>
		<div class="panel opensource">
			<div class="status-title">
				<span>{{ status.opensource }}</span
				>오픈소스
			</div>
			<div class="tooltip-content">
				<p class="tooltip-title">{{ msg.opensource }}</p>
			</div>
		</div>
		<div class="panel license">
			<div class="status-title">
				<span>{{ status.license }}</span
				>라이선스
			</div>
			<div class="tooltip-content">
				<p class="tooltip-title">{{ msg.license }}</p>
			</div>
		</div>
		<div class="panel warning">
			<div class="status-title">
				<span>{{ status.warning }}</span
				>주의
			</div>
			<div class="tooltip-content">
				<p class="tooltip-title">{{ msg.warning }}</p>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "StatusOverview",
	data() {
		return {
			msg: {
				project: "검증된 프로젝트 수 입니다.",
				opensource: "검증된 프로젝트의 오픈소스 종류입니다.",
				license: "검증된 프로젝트의 라이선스의 종류입니다.",
				warning: "사용 시 주의해야하는 라이선스 개수입니다.",
			},
		};
	},
	props: {
		status: Object,
	},
};
</script>
<style
	lang="scss"
	scoped
	src="../../assets/css/MyProject/StatusOverview.scss"
></style>
