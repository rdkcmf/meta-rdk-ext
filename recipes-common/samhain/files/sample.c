#include <unistd.h>
#include <stdio.h>

int main(void)
{
    //char *argv[] = { "/bin/sh", "-c", "env", 0 };
    char *argv[] = {"/bin/sh"};
/*
    char *envp[] =
    {
        "HOME=/",
        "PATH=/bin:/usr/bin",
        "TZ=UTC0",
        "USER=beelzebub",
        "LOGNAME=tarzan",
        0
    };
*/
    char *envp[] = { "/hello.sh"};
    execve(argv[0], &argv[0], envp);
    printf("Hiii \n");
    fprintf(stderr, "Oops!\n");
    return -1;
}

