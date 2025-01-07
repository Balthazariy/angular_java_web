export interface Request {
    id: number;
    filteredId: number;
    workerId: number;
    statusId: number;
    roleId: number;
    serviceId: number;
    requestDate: Date;
    approvalDate: Date;
    rejectionReason: string;
    statusName: string;
    roleName: string;
    serviceName: string;
  }