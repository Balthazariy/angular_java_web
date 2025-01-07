export type ArtRole = {
    id: number,
    name: string
}

export type ArtStatus = {
    id: number,
    name: string
}

export type ArtService = {
    id: number,
    name: string,
    description: string
}

export type ArtPosition = {
    id: number,
    name: string
}

export type ArtDepartment = {
    id: number,
    name: string
}

export type ArtAccess = {
    id: number,
    userId: number,
    serviceId: number,
    roleId: number,
    description: string,
    roleName: string,
    serviceName: string
}

export type ArtRequest = {
    id: number,
    workerId: number,
    statusId: number,
    roleId: number,
    requestDate: Date,
    approvalDate: Date,
    rejectionReason: string,
    serviceId: number
}

export type ArtWorker = {
    workerId: number,
    hireDate: Date,
    fullname: string,
    departmentId: number,
    positionId: number
}

export type ArtUser = {
    id: number,
    login: string,
    password: string,
    workerId: number
}