<template>
	<div>
		<my-project-path :department="'마이페이지'" />
		<h1 id="mypage-profile-title">Profile</h1>
		<div id="mypage-profile-container">
			<div id="mypage-profile-content">
				<section class="mypage-section-image">
					<img
						id="mypage-profile-image"
						:src="imageList[profile.userImg - 1].url"
						:alt="imageList[profile.userImg - 1].alt"
					/>
				</section>
				<section class="mypage-section-info">
					<div
						class="mypage-profile-info-image"
						v-for="(image, index) in imageList"
						:key="`${index + 1}_imageList`"
					>
						<input
							:id="`profile-image-${index + 1}`"
							type="radio"
							:value="index + 1"
							v-model="profile.userImg"
						/>
						<label :for="`profile-image-${index + 1}`">
							<div>
								<img :src="image.url" :alt="image.alt" />
							</div>
						</label>
					</div>
					<table id="mypage-profile-table">
						<tbody>
							<tr>
								<td class="table-left">이름</td>
								<td class="table-right">
									<input type="text" v-model="profile.name" required />
								</td>
							</tr>
							<tr>
								<td class="table-left">부서</td>
								<td class="table-right">
									<select
										name="department"
										id="mypage-select"
										v-model="profile.depart"
										required
									>
										<option
											v-for="(value, index) in departmentList"
											:key="`${index}_departmentList`"
											:value="index + 1"
										>
											{{ value }}
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="table-left">직급</td>
								<td class="table-right">
									<select
										name="job"
										id="mypage-select"
										v-model="profile.job"
										required
									>
										<option
											v-for="(value, index) in jobList"
											:key="`${index}_jobList`"
											:value="index + 1"
										>
											{{ value }}
										</option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</section>
			</div>
			<button id="submit-button" @click="ChangeProfile">확인</button>
		</div>
	</div>
</template>
<script>
import Info from "@/api/info.js";
import { mapGetters } from "vuex";
import MyProjectPath from "../../components/MyProject/MyProjectPath.vue";
export default {
	name: "MyPageProfile",
	components: { MyProjectPath },
	data() {
		return {
			imageList: [],
			departmentList: [],
			jobList: [],
			profile: {
				userImg: 1,
				name: "김신한",
				depart: 1,
				job: 1,
			},
		};
	},
	methods: {
		ChangeProfile: function () {
			this.$store.dispatch("UPDATEUSER", this.profile);
		},
	},
	computed: {
		...mapGetters(["getName", "getJob", "getDepartment", "getUserImg"]),
	},
	created() {
		this.imageList = Info.GetProfileImageList();
		this.departmentList = Info.GetDepartmentList();
		this.jobList = Info.GetJobList();
		this.profile.userImg = this.getUserImg;
		this.profile.name = this.getName;
		this.profile.depart = this.getDepartment;
		this.profile.job = this.getJob;
	},
};
</script>
<style scoped src="@/assets/css/MyPage/MyPageProfile.css"></style>
