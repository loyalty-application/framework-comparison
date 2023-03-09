# Introduction

This project aims to answer the question "Is it worth the effort to learn a new language?"

# Technical

This repository is a Mono Repo of multiple projects in different languages.

In the following sub folders, you will find barebone projects using specific REST API frameworks:
- [flask/ (Flask Project)](https://github.com/soonann/framework-comparison/tree/main/flask)
- [spring-boot/ (Spring Boot Project)](https://github.com/soonann/framework-comparison/tree/main/spring-boot)
- [node-js/ (NodeJS Express Project)](https://github.com/soonann/framework-comparison/tree/main/node-js)
- [go-gin/ (Go-lang Gin Project)](https://github.com/soonann/framework-comparison/tree/main/go-gin)
- [asp-dot-net/(ASP.NET MVC Project)](https://github.com/soonann/framework-comparison/tree/main/asp-dot-net)

# Setup

To setup an Amazon-Linux-AMI on EC2, you can run the following script to install the dependencies and clone the repository

```bash
wget -O - https://raw.githubusercontent.com/loyalty-application/framework-comparison/main/aws-setup.sh | bash
```
Note that after running this script, it will open up the repository in a `tmux` session.

If you do not wish to use tmux, you can exit it with the command

```bash
exit
```
If you wish to, you can also use the host file provided in the root directory of this project, and fill up the hostname ip for all the instances

## Tmux (Getting Started)
You do not need to use tmux if you don't want to.
However, if you wish to keep the tmux session, you can run commands on the pane and detach from it with the shortcut `Ctrl+B` `D`. (Note that this means you should press `Ctrl+B` first then press `D`)

### Attach Tmux Pane
To re-attach to the pane, you can simply do:
```bash
tmux a
```

### Attaching to a specific Tmux Pane
When you have multiple panes, you might want to attach to a specific one, you can use the `tmux ls` command to list all the panes available:
```bash
tmux ls
# 0: pane ....
# 1: pane ....
```

To attach to a specific pane, you can use the followign command:

```bash
tmux a -t <pane-no>

# for example if I want to attach to pane index 0, I will use the command:
# tmux a -t 0
```

