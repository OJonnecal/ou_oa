import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Information from './views/Information.vue'
import Meetingroom from './views/manage/Meetingroom.vue'
import Customer from './views/manage/Customer.vue'
import User from './views/manage/User.vue'
import Project from './views/manage/Project.vue'
import Clockin from './views/personal/Clockin.vue'
import Assignment from './views/personal/Assignment.vue'
import Contacts from './views/personal/Contacts.vue'
import Leave from './views/process/Leave.vue'
import Expense from './views/process/Expense.vue'
import AskForProject from './views/process/AskForProject.vue'
import Quit from './views/process/Quit.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'el-icon-news',
        leaf: true,//只有一个节点
        children: [
            { path: '/Information', component: Information, name: '信息中心' }
        ]
    },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '',
    //     iconCls: 'fa fa-address-card',
    //     leaf: true,//只有一个节点
    //     children: [
    //         { path: '/upload', component: upload, name: '文件管理平台' }
    //     ]
    // },
    {
        path: '/',
        component: Home,
        name: '管理中心',
        iconCls: 'el-icon-s-management',//图标样式class
        children: [
            // { path: '/Approve', component: Approve, name: '请假/审批' },
            // { path: '/Log', component: Log, name: '日志系统' },
            { path: '/Meetingroom', component: Meetingroom, name: '会议室管理' },
            { path: '/Customer', component: Customer, name: '客户管理' },
            { path: '/User', component: User, name: '用户管理' },
            { path: '/Project', component: Project, name: '项目管理' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '个人中心',
        iconCls: 'el-icon-s-custom',//图标样式class
        children: [
            { path: '/Clockin', component: Clockin, name: '考勤列表' },
            { path: '/Assignment', component: Assignment, name: '任务清单' },
            { path: '/Contacts', component: Contacts, name: '通讯录' },
            // { path: '/Message', component: Message, name: '消息提醒' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '流程管理',
        iconCls: 'el-icon-tickets',//图标样式class
        children: [
            { path: '/AskForProject', component: AskForProject, name: '项目申请/审批' },
            { path: '/Leave', component: Leave, name: '请假申请/审批' },
            { path: '/Expense', component: Expense, name: '报销申请/审批' },
            { path: '/Quit', component: Quit, name: '离职申请/审批' },
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;