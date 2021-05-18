<template>
	<div class="repository-card-background">
		<div class="repository-card">
			<div v-if="repository.verified" class="fade-out"></div>
			<div class="repository-name">
				<p>{{ repository.name }}</p>
				<p class="repository-name-branch" v-if="repository.branch">
					{{ repository.branch }}
				</p>
			</div>

			<!-- 이미 검증된 프로젝트 -->
			<div v-if="repository.verified" class="button-area verified">
				<span class="icon material-icons"> done </span>
			</div>
			<!-- 선택된 프로젝트 -->
			<div
				v-else-if="selected"
				class="button-area selected"
				@click="RemoveCard"
			>
				<span class="icon back-arrow-icon material-icons">
					arrow_back_ios
				</span>
			</div>
			<!-- 리스트에서 고른 프로젝트 -->
			<div v-else-if="checked" class="button-area checked">
				<span class="icon material-icons"> add_task </span>
			</div>
			<div v-else class="button-area" @click="SelectCard">
				<span class="icon material-icons"> add </span>
			</div>
			<!-- <div
			class="button-area"
			:class="{ checked: repository.verified }"
			@click="CardClick"
		>
			<span v-if="selected" class="icon back-arrow-icon material-icons">
				arrow_back_ios
			</span>
			<span
				v-else-if="repository.verified"
				class="icon done-icon material-icons"
			>
				done
			</span>
			<span v-else class="icon material-icons"> add </span>
		</div> -->
			<img
				v-if="gitType == 1"
				id="scm-icon"
				src="@/assets/images/gitlab-icon.png"
				alt="gitlab-icon"
			/>
			<img
				v-else
				id="scm-icon"
				src="@/assets/images/github-icon.png"
				alt="gitlab-icon"
			/>
		</div>
		<!-- 이미 검증된 프로젝트 설명 -->
		<div v-if="repository.verified" class="repository-card-info">
			{{ infoText }}
		</div>
	</div>
</template>
<script>
export default {
	name: "RepositoryCard",
	data() {
		return {
			infoText: "이미 검증된 프로젝트입니다.",
		};
	},
	props: {
		//레포지토리 정보
		repository: Object,

		//selected칸에 포함된 카드
		selected: Boolean,

		//형상관리칸에 있는 카드 중 selected된 카드
		checked: {
			type: Boolean,
			default: false,
		},
		gitType: Number,
	},
	created() {
		// console.log(this.gitType);
	},
	methods: {
		SelectCard: function () {
			//레포지토리 추가
			this.$emit("addRepoClick", this.repository);
		},
		RemoveCard: function () {
			//선택된 레포지토리 제거
			this.$emit("selectedRepoClick", this.repository.id);
		},
	},
};
</script>
<style scoped src="@/assets/css/MyProject/RepositoryCard.css"></style>
